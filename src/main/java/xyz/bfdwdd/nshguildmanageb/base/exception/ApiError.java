package xyz.bfdwdd.nshguildmanageb.base.exception;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<String> errors;
}