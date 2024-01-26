package id.ac.poltekgt.backend.models.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToolDAO {
    
    private String name;

    private Integer currentQuantity;

    private Integer totalQuantity;
}
