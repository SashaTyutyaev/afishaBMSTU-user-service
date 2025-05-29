package afishaBMSTU.user_service.dto;

import lombok.Value;

/**
 * DTO for {@link afishaBMSTU.user_service.model.email.Email}
 */
@Value
public class EmailDto {
    Long id;
    String email;
}