package afishaBMSTU.user_service.controller.user;

import afishaBMSTU.user_service.dto.UserContactInfoUpdateDto;
import afishaBMSTU.user_service.model.user.CustomUserDetails;
import afishaBMSTU.user_service.service.contactInfo.MainUserContactInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/contact-info")
@RequiredArgsConstructor
@Tag(name = "API для управления контактной информацией пользователями")
public class UserContactInfoController {

    private final MainUserContactInfoService userManagingService;

    @Operation(summary = "Добавление новой почты или телефона", description = "Добавляет новый email или телефон пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Элемент успешно добавлен"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PostMapping("/add")
    public void addNewItem(
            @RequestBody @Valid UserContactInfoUpdateDto userContactInfoUpdateDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        userManagingService.addNewItem(userDetails.getId(), userContactInfoUpdateDto);
    }

    @Operation(summary = "Обновление почты или телефона", description = "Обновляет email или телефон пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Элемент успешно обновлен"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "404", description = "Элемент не найден"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PatchMapping("/update/{itemId}")
    public void updateItem(
            @RequestBody @Valid UserContactInfoUpdateDto userContactInfoUpdateDto,
            @PathVariable Long itemId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        userManagingService.updateItem(itemId, userDetails.getId(), userContactInfoUpdateDto);
    }

    @Operation(summary = "Удаление почты или телефона", description = "Удаляет email или телефон пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Элемент успешно удален"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "404", description = "Элемент не найден")
    })
    @DeleteMapping("/delete/{itemId}")
    public void deleteItem(
            @Parameter(description = "Тип элемента (email или phone)") @RequestParam String type,
            @PathVariable Long itemId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        userManagingService.deleteItem(itemId, userDetails.getId(), type);
    }
}
