package afishaBMSTU.user_service.controller.user;

import afishaBMSTU.user_service.dto.UserFullDto;
import afishaBMSTU.user_service.dto.UserUpdateDto;
import afishaBMSTU.user_service.model.user.CustomUserDetails;
import afishaBMSTU.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "API для манипуляций с пользователем", description = "API для управления пользовательскими данными")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Получить данные пользователя",
            description = "Возвращает полную информацию о текущем авторизованном пользователе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные пользователя",
                            content = @Content(schema = @Schema(implementation = UserFullDto.class))),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация")
            }
    )
    @GetMapping
    public UserFullDto getUser(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return userService.getUser(userDetails.getId());
    }

    @Operation(
            summary = "Обновить данные пользователя",
            description = "Обновить информацию о текущем авторизованном пользователе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные пользователя",
                            content = @Content(schema = @Schema(implementation = UserFullDto.class))),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация")
            }
    )
    @PatchMapping
    public UserFullDto updateUser(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails, UserUpdateDto userUpdateDto
    ) {
        return userService.updateUser(userUpdateDto, userDetails.getId());
    }

    @Operation(
            summary = "Удалить пользователя",
            description = "Удаляет аккаунт текущего пользователя",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация")
            }
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        userService.deleteUser(userDetails.getId(), userDetails.getExternalId());
    }
}