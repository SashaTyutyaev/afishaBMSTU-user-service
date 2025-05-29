package afishaBMSTU.user_service.kafka;

import afishaBMSTU.user_service.dto.UserCreationRequestDto;
import afishaBMSTU.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final UserService userService;

    @KafkaListener(topics = "#{'${integration.kafka.topic.user-creation}'}",
            properties = {"spring.json.value.default.type=afishaBMSTU.user_service.dto.UserCreationRequestDto"})
    public void listen(UserCreationRequestDto userCreationRequestDto) {
        if (userCreationRequestDto != null) {
            userService.createUserAfterSignUp(userCreationRequestDto);
        }
    }
}
