package id.ac.poltekgt.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.ac.poltekgt.backend.models.User;
import id.ac.poltekgt.backend.payload.response.MessageResponseQueryList;
import id.ac.poltekgt.backend.payload.response.MessageResponseQuerySingle;
import id.ac.poltekgt.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> getAll() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        return ResponseEntity
                .ok("ok");
    }

    public ResponseEntity<?> getById(Integer id) {
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity
                    .ok(MessageResponseQuerySingle.builder().success(false).message("User not found!").build());
        }

        return ResponseEntity.ok(userRepository.findById(id).get());
    }
}
