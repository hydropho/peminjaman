package id.ac.poltekgt.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToolDTO {
    
    private Integer id;

    private String name;

    private String filename;

    private Integer current_quantity;

    private Integer total_quantity;
}
