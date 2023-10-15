package id.ac.poltekgt.backend.payload.response;

import java.util.List;

import id.ac.poltekgt.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseQueryList {

    private boolean success;

    private List<User> data;

    private String message;

}
