package id.ac.poltekgt.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.ac.poltekgt.backend.models.Role;
import id.ac.poltekgt.backend.models.User;
import id.ac.poltekgt.backend.payload.request.LoginRequest;
import id.ac.poltekgt.backend.payload.request.RegisterRequest;
import id.ac.poltekgt.backend.payload.response.JwtResponse;
import id.ac.poltekgt.backend.payload.response.MessageResponseAuth;
import id.ac.poltekgt.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.existsByNim(request.getNim())) {
            return ResponseEntity.ok(MessageResponseAuth
                    .builder()
                    .success(false)
                    .message("NIM already exist!")
                    .build());
        }

        Role role;

        if (request.getRole() == null || request.getRole() == "") {
            role = Role.builder().id(2).build();
        } else {
            role = Role.builder().id(1).build();
        }

        var user = User.builder()
                .name(request.getName())
                .nim(request.getNim())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(MessageResponseAuth
                .builder()
                .success(true)
                .message("User registered successfully!")
                .build());
    }

    public ResponseEntity<?> login(LoginRequest request) {
        if (!userRepository.findByNim(request.getNim()).isPresent()) {
            return ResponseEntity.ok(MessageResponseAuth
                    .builder()
                    .success(false)
                    .message("NIM doesn't exist!")
                    .build());
        }

        var user = userRepository.findByNim(request.getNim()).get();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getNim(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            return ResponseEntity.ok(MessageResponseAuth
                    .builder()
                    .success(false)
                    .message("Password is wrong!")
                    .build());
        }

        var jwtToken = jwtService.generateToken(user);

        JwtResponse jwtResponse = JwtResponse
                                    .builder()
                                    .token(jwtToken)
                                    .id(user.getId())
                                    .nim(user.getNim())
                                    .name(user.getName())
                                    .role(user.getRole().getName())
                                    .build();

        return ResponseEntity.ok(MessageResponseAuth
                .builder()
                .success(true)
                .message("Login Success!")
                .data(jwtResponse)
                .build());
    }

}
