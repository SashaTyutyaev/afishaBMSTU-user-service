package afishaBMSTU.user_service.config;

import afishaBMSTU.user_service.client.InternalTokenFeignInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InternalFeignConfig {

    @Bean
    public RequestInterceptor internalTokenFeignInterceptor() {
        return new InternalTokenFeignInterceptor();
    }
}
