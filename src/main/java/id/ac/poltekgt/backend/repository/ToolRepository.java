package id.ac.poltekgt.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.poltekgt.backend.models.Tool;


@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {
    Boolean existsByName(String name);
}
