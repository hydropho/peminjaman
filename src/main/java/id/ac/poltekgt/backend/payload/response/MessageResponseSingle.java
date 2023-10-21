package id.ac.poltekgt.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseSingle {

    private boolean success;

    private Object data;

    private String message;

}
