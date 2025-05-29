package afishaBMSTU.user_service.dto;

import lombok.Value;

/**
 * DTO for {@link afishaBMSTU.user_service.model.phone.Phone}
 */
@Value
public class PhoneDto {
    Long id;
    String phone;
}