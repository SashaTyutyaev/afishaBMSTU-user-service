package afishaBMSTU.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.UUID;

@FeignClient(name = "auth-service", url = "${integration.rest.auth-service.url}")
public interface AuthServiceFeignClient {

    @DeleteMapping("/api/internal")
    void deleteUser(UUID externalId);
}
