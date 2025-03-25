package afishaBMSTU.user_service.security;

import afishaBMSTU.auth_lib.security.BaseJwtService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService extends BaseJwtService<UUID> {

    @Override
    protected Class<UUID> getDataType() {
        return UUID.class;
    }

}
