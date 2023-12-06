package id.ac.poltekgt.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import id.ac.poltekgt.backend.models.dao.ToolDAO;
import id.ac.poltekgt.backend.service.ToolService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tool")
@RequiredArgsConstructor
public class ToolController {
    
    private final ToolService toolService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return toolService.getAll();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return toolService.getByID(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestPart("data") ToolDAO tool, @RequestPart("image") MultipartFile image) {
        return toolService.add(tool, image);
    }

    @GetMapping("/getImage/{filename}")
    public ResponseEntity<?> getByImage(@PathVariable String filename) {
        return toolService.getImage(filename);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestPart("data") ToolDAO tool, @RequestPart("image") MultipartFile image) {
        return toolService.update(id, tool, image);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return toolService.delete(id);
    }
}
