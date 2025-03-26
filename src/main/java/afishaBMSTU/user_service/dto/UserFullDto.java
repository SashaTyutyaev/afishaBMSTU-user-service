package afishaBMSTU.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDto {
    private String name;
    private String surname;
    private String groupName;
    private Set<String> emails;
    private Set<String> phones;
}
