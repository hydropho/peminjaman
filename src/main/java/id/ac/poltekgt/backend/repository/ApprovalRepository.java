package id.ac.poltekgt.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.poltekgt.backend.models.Approval;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Integer> {

}
