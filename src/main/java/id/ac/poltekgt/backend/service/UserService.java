package id.ac.poltekgt.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.ac.poltekgt.backend.models.User;
import id.ac.poltekgt.backend.models.dao.UserDAO;
import id.ac.poltekgt.backend.models.dto.UserDTO;
import id.ac.poltekgt.backend.payload.response.MessageResponseList;
import id.ac.poltekgt.backend.payload.response.MessageResponseSingle;
import id.ac.poltekgt.backend.repository.RoleRepository;
import id.ac.poltekgt.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public ResponseEntity<?> getAll() {
        if ( userRepository.findAll().isEmpty() ) {
            return ResponseEntity.ok(MessageResponseList
                    .builder()
                    .success(false)
                    .message("Users not found!")
                    .build());
        }

        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        users.forEach(user -> {
            userDTOs.add( UserDTO
                            .builder()
                            .id(user.getId())
                            .name(user.getName())
                            .nim(user.getNim())
                            .role(user.getRole()
                            .getName())
                            .build());
        });

        return ResponseEntity.ok(MessageResponseList
                .builder()
                .success(true)
                .message("Users exist!")
                .data(userDTOs)
                .build());
    }

    public ResponseEntity<?> getById(Integer id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("User not exist!")
                    .build());
        }

        User user = userRepository.findById(id).get();

        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("User exist!")
                .data(UserDTO
                    .builder()
                    .id(id)
                    .nim(user.getNim())
                    .name(user.getName())
                    .role(user.getRole().getName())
                    .build())
                .build());
    }

    public ResponseEntity<?> update(Integer id, UserDAO newUser) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("User not exist!")
                    .build());
        }

        User user = userRepository.findById(id).get();

        user.setName(newUser.getName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(roleRepository.findById(newUser.getRole_id()).get());

        userRepository.save(user);
        
        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("User updated!")
                .data(UserDTO
                    .builder()
                    .id(id)
                    .nim(user.getNim())
                    .name(user.getName())
                    .role(user.getRole().getName())
                    .build())
                .build());
    }

    public ResponseEntity<?> delete(Integer id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.ok(MessageResponseSingle
                    .builder()
                    .success(false)
                    .message("User not exist!")
                    .build());
        }

        userRepository.deleteById(id);

        return ResponseEntity.ok(MessageResponseSingle
                .builder()
                .success(true)
                .message("User deleted!")
                .build());
    }
}
