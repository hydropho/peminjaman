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

    private Integer current_quantity;

    private Integer total_quantity;
}
