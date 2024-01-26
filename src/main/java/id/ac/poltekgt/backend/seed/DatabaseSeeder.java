package id.ac.poltekgt.backend.seed;

import java.util.logging.Logger;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import id.ac.poltekgt.backend.models.Role;
import id.ac.poltekgt.backend.models.User;
import id.ac.poltekgt.backend.repository.RoleRepository;
import id.ac.poltekgt.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {
    private Logger logger = Logger.getLogger(String.valueOf(DatabaseSeeder.class));

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedUserTable();
    }
    
    private void seedRoleTable() {
        if (!roleRepository.existsById(1)) {
            Role admin = Role.builder()
                            .name("ROLE_ADMIN")
                            .build();
    
            roleRepository.save(admin);
            logger.info("Role admin added.");
        } else {
            logger.info("Role admin already exist.");
        }
        
        if (!roleRepository.existsById(2)) {
            Role user = Role.builder()
            .name("ROLE_USER")
            .build();
            
            roleRepository.save(user);
            logger.info("Role user added.");    
        } else {
            logger.info("Role admin already exist.");
        }
    }

    private void seedUserTable() {
        if (!userRepository.existsById(1)){
            User admin = User.builder()
                            .name("Admin")
                            .nim("1111111")
                            .password(new BCryptPasswordEncoder().encode("Admin@pgt123"))
                            .role(roleRepository.findById(1).get())
                            .build();
                     
            userRepository.save(admin);
            logger.info("User admin created.");
        } else {
            logger.info("User admin already exist.");
        }

    }

}
