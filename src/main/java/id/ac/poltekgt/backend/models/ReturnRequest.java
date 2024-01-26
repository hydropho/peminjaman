package id.ac.poltekgt.backend.models;

import java.sql.Date;
import java.util.List;

import id.ac.poltekgt.backend.models.Enum.EApprovalStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "return_request")
public class ReturnRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Tool tool;

    @NotNull
    private Date created_at;

    private Date approved_at;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private EApprovalStatus approval_status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "return_request")
    private List<BorrowedTool> borrowedTools;
}
