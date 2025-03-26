package afishaBMSTU.user_service.model.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CustomUserDetails {
    private final Long id;
    private final UUID externalId;
    private final String name;
    private final String surname;
    private final List<String> roles;
}
