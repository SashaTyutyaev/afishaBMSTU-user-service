package afishaBMSTU.user_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${integration.kafka.topic.user-delete}")
    private String userDeleteTopic;

    @Bean
    public NewTopic userCreationTopic() {
        return TopicBuilder.name(userDeleteTopic)
                .replicas(1)
                .build();
    }

}
