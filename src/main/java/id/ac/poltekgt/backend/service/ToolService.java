package id.ac.poltekgt.backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import id.ac.poltekgt.backend.models.Tool;
import id.ac.poltekgt.backend.models.dao.ToolDAO;
import id.ac.poltekgt.backend.models.dto.ToolDTO;
import id.ac.poltekgt.backend.payload.response.MessageResponseList;
import id.ac.poltekgt.backend.payload.response.MessageResponseSingle;
import id.ac.poltekgt.backend.repository.ToolRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolService {

    private final ToolRepository toolRepository;

    @Value("${app.file.allowed-file-extention}")
    private List<String> allowedFileExtention;

    @Value("${app.file.base-directory}")
    private String baseDirectory;
    
    public ResponseEntity<?> getAll() {
        if (toolRepository.findAll().isEmpty()){
            return ResponseEntity.ok(MessageResponseList
                    .builder()
                    .success(false)
                    .message("Tools not found!")
                    .build());
        }

        List<Tool> tools = toolRepository.findAll();
        List<ToolDTO> toolDTOs = new ArrayList<>();

        tools.forEach(tool -> {
            toolDTOs.add( ToolDTO
                            .builder()
                            .id(tool.getId())
                            .name(tool.getName())
                            .filename(tool.getFile_name())
                            .current_quantity(tool.getCurrent_quantity())
                            .total_quantity(tool.getTotal_quantity())
                            .build());
        });

        return ResponseEntity.ok(MessageResponseList
                .builder()
                .success(true)
                .message("Tools found!")
                .data(toolDTOs)
                .build());
    }

    public ResponseEntity<?> getByID(Integer id) {
        if (!toolRepository.existsById(id)) {
            return ResponseEntity.ok(MessageResponseSingle
                                        .builder()
                                        .success(false)
                                        .message("Tool not exist!")
                                        .build());
        }

        Tool tool = toolRepository.findById(id).get();

        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("Tool exist!")
                .data(ToolDTO
                    .builder()
                    .id(id)
                    .name(tool.getName())
                    .filename(tool.getFile_name())
                    .current_quantity(tool.getCurrent_quantity())
                    .total_quantity(tool.getTotal_quantity())
                    .build())
                .build());
    }

    public ResponseEntity<?> add(ToolDAO request, MultipartFile image) {
        if(toolRepository.existsByName(request.getName())) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Tool already exist!")
                    .build());
        }

        if(request.getCurrentQuantity() != request.getTotalQuantity()) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Current quantity and total quantity must be the same!")
                    .build());
        }

        if(request.getCurrentQuantity() > request.getTotalQuantity()) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Current quantity cannot be higher than total quantity!")
                    .build());
        }

        if(image.isEmpty()) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Image cannot be empty!")
                    .build());
        }

        if(!allowedFileExtention.contains(image.getContentType())){
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Only JPG, JPEG, and PNG are allowed!")
                    .build());
        }

        String file_name = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(image.getOriginalFilename());

        String file_path = baseDirectory + file_name;

        try {
            image.transferTo(new File(file_path));
        } catch (IllegalStateException | IOException e) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Failed uploading image, please try again!")
                    .build());
        }

        Tool tool =  Tool.builder()
                .name(request.getName())
                .current_quantity(request.getCurrentQuantity())
                .total_quantity(request.getTotalQuantity())
                .file_name(file_name)
                .build();

        toolRepository.save(tool);

        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("Tool added successfully!")
                .build());
    }

    public ResponseEntity<?> getImage(String filename) {
        String filepath = baseDirectory + filename;
        byte[] image;

        try {
            image = Files.readAllBytes(new File(filepath).toPath());
        } catch (IOException e) {
            return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(false)
                .message("Image not found!")
                .build());
        }

        String fileExtention = StringUtils.getFilenameExtension(filename);

        return ResponseEntity.ok().contentType(MediaType.valueOf("image/"+fileExtention)).body(image);
    }

    public ResponseEntity<?> update(Integer id, ToolDAO newTool, MultipartFile image) {
        if(!toolRepository.existsById(id)) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Tool not exist!")
                    .build());
        }

        if (newTool.getCurrentQuantity() > newTool.getTotalQuantity()) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Current quantity cannot be higher than total quantity!")
                    .build());
        }

        Tool tool = toolRepository.findById(id).get();

        if(image.getOriginalFilename() != tool.getFile_name()) {
            try {
                Files.delete(new File(baseDirectory+tool.getFile_name()).toPath());
            } catch (IOException e) {
                return ResponseEntity.ok(MessageResponseSingle
                        .builder()
                        .success(false)
                        .message("Failed deleting image, please try again!")
                        .build());
            }

            String file_name = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(image.getOriginalFilename());

            String file_path = baseDirectory + file_name;
            
            
            try {
                image.transferTo(new File(file_path));
                tool.setFile_name(file_name);
            } catch (IllegalStateException | IOException e) {
                return ResponseEntity.ok(MessageResponseSingle
                        .builder()
                        .success(false)
                        .message("Failed uploading image, please try again!")
                        .build());
            }
        }

        tool.setName(newTool.getName());
        tool.setCurrent_quantity(newTool.getCurrentQuantity());
        tool.setTotal_quantity(newTool.getTotalQuantity());

        toolRepository.save(tool);

        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("Tool updated!")
                .build());
    }
    
    public ResponseEntity<?> delete(Integer id) {
        if(!toolRepository.existsById(id)) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Tool not exist!")
                    .build());
        }

        Tool tool = toolRepository.findById(id).get();

        try {
            Files.delete(new File(baseDirectory+tool.getFile_name()).toPath());
        } catch (IOException e) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Failed deleting image, please try again!")
                    .build());
        }

        toolRepository.deleteById(id);

        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("Tool deleted!")
                .build());
    }
}
