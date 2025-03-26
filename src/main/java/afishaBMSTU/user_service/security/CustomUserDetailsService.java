package afishaBMSTU.user_service.security;

import afishaBMSTU.user_service.exceptions.NotFoundException;
import afishaBMSTU.user_service.model.user.CustomUserDetails;
import afishaBMSTU.user_service.model.user.User;
import afishaBMSTU.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public CustomUserDetails loadUser(UUID externalId, List<String> roles) {
        User user = userRepository.findByExternalId(externalId).orElseThrow(() -> {
            log.error("User with externalId {} not found", externalId);
            return new NotFoundException("User with externalId " + externalId + " not found");
        });

        return new CustomUserDetails(user.getId(), externalId, user.getName(), user.getSurname(), roles);
    }
}
