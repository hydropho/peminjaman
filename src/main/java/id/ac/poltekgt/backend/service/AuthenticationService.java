package id.ac.poltekgt.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.ac.poltekgt.backend.models.ERole;
import id.ac.poltekgt.backend.models.User;
import id.ac.poltekgt.backend.payload.request.LoginRequest;
import id.ac.poltekgt.backend.payload.request.RegisterRequest;
import id.ac.poltekgt.backend.payload.response.JwtResponse;
import id.ac.poltekgt.backend.payload.response.MessageResponse;
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
            return ResponseEntity.badRequest().body(MessageResponse.builder().message("NIM already exist").build());
        }

        var user = User.builder()
                .name(request.getName())
                .nim(request.getNim())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.USER)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(MessageResponse.builder().message("User registered successful").build());
    }

    public ResponseEntity<?> login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNim(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = userRepository.findByNim(request.getNim()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(JwtResponse.builder().token(jwtToken).id(user.getId()).nim(user.getNim())
                .name(user.getName()).role(user.getRole().name()).build());
    }

}
