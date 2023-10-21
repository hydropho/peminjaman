package id.ac.poltekgt.backend.models.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDAO {
    
    private String name;

    private String password;

    private Integer role_id;
    
}
