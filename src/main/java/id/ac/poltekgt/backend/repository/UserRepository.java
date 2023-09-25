package id.ac.poltekgt.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.poltekgt.backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
    Optional<User> findByNim(String nim);

    Boolean existsByNim(String nim);
}
