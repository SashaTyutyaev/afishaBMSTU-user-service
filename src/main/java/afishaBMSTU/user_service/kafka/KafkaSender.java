package afishaBMSTU.user_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaSender {

    @Value("${integration.kafka.topic.user-delete}")
    private String userDeleteTopic;

    private final KafkaTemplate<String, UUID> kafkaTemplate;

    public void send(UUID externalId) {
        kafkaTemplate.send(userDeleteTopic, externalId);
        log.info("Successfully sent user deleted with externalId: {}", externalId);
    }
}
