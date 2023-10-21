package id.ac.poltekgt.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> add(@RequestBody ToolDAO tool) {
        return toolService.add(tool);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ToolDAO tool) {
        return toolService.update(id, tool);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return toolService.delete(id);
    }
}
