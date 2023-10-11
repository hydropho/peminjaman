package id.ac.poltekgt.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    
    private boolean success;

    private String messageType;

    private JwtResponse data;
    
    private String message;

}
