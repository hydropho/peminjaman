package id.ac.poltekgt.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
                    .current_quantity(tool.getCurrent_quantity())
                    .total_quantity(tool.getTotal_quantity())
                    .build())
                .build());
    }

    public ResponseEntity<?> add(ToolDAO request) {
        if(toolRepository.existsByName(request.getName())) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Tool already exist!")
                    .build());
        }

        if(request.getCurrent_quantity() != request.getTotal_quantity()) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Current quantity and total quantity must be the same!")
                    .build());
        }
        if(request.getCurrent_quantity() > request.getTotal_quantity()) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Current quantity cannot be higher than total quantity!")
                    .build());
        }
        
        Tool tool =  Tool.builder()
                .name(request.getName())
                .current_quantity(request.getCurrent_quantity())
                .total_quantity(request.getTotal_quantity())
                .build();

        toolRepository.save(tool);

        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("Tool added successfully!")
                .build());
    }

    public ResponseEntity<?> update(Integer id, ToolDAO newTool) {
        if(!toolRepository.existsById(id)) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Tool not exist!")
                    .build());
        }

        if (newTool.getCurrent_quantity() > newTool.getTotal_quantity()) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("Current quantity cannot be higher than total quantity!")
                    .build());
        }

        Tool tool = toolRepository.findById(id).get();

        tool.setName(newTool.getName());
        tool.setCurrent_quantity(newTool.getCurrent_quantity());
        tool.setTotal_quantity(newTool.getTotal_quantity());

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

        toolRepository.deleteById(id);

        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("Tool deleted!")
                .build());
    }
}
