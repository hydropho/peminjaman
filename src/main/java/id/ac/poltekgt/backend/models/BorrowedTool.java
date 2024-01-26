package id.ac.poltekgt.backend.models;

import java.sql.Date;

import id.ac.poltekgt.backend.models.Enum.EBorrowingStatus;
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
@Table(name = "borrowed_tools")
public class BorrowedTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    private BorrowRequest borrow_request;

    @ManyToOne
    private ReturnRequest return_request;

    @NotNull
    private Date borrowed_at;

    private Date returned_at;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EBorrowingStatus borrowing_status;
}
