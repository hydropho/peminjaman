package id.ac.poltekgt.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.ac.poltekgt.backend.service.UserService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return userService.getAll();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return userService.getById(id);
    }
}
