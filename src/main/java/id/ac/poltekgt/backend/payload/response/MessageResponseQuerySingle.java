package id.ac.poltekgt.backend.payload.response;

import id.ac.poltekgt.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseQuerySingle {

    private boolean success;

    private User data;

    private String message;

}
