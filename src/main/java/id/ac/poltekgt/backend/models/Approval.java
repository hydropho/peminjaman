package id.ac.poltekgt.backend.models;

import java.sql.Date;

import id.ac.poltekgt.backend.models.Enum.EStatus;
import id.ac.poltekgt.backend.models.Enum.EType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "approvals")
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EType type;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    private Date created_at;

    private Date approved_at;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EStatus status;
}
