package id.ac.poltekgt.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    
    @GetMapping
    public ResponseEntity<String> sayHello() {
        // System.out.println();
        return ResponseEntity.ok("Hello from secure");
    }
    
    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> ssayHello() {
        return ResponseEntity.ok("done ya bang");
    }
    @GetMapping("/test1")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sssayHello() {
        return ResponseEntity.ok("done ya bang");
    }
}
