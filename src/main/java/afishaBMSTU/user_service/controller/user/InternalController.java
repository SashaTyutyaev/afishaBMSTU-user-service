package afishaBMSTU.user_service.controller.user;

import afishaBMSTU.user_service.dto.UserCreationRequestDto;
import afishaBMSTU.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
@Hidden
public class InternalController {

    private final UserService userService;

    @PostMapping("/create-user-after-sign-up")
    public void createUserAfterSignUp(@RequestBody UserCreationRequestDto userCreationRequestDto) {
        userService.createUserAfterSignUp(userCreationRequestDto);
    }
}
