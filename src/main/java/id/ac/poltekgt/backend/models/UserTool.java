package id.ac.poltekgt.backend.models;

import java.sql.Date;

import id.ac.poltekgt.backend.models.Enum.EStatus;
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
@Table(name = "user_tools")
public class UserTool {

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
    private Integer quantity;

    private Date borrowed_at;

    private Date returned_at;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EStatus status;
}
