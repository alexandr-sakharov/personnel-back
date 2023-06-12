package ru.personnel.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@Schema(description = "Обновление данных пользователя", title = "UserInDto")
public class UserInDto {
    @Schema(
            description = "Старое имя пользователя",
            example = "username"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String oldUsername;


    @Schema(
            description = "Новое имя пользователя",
            example = "username"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String newUsername;


    @Schema(
            description = "Старый пароль",
            example = "Test1234"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String oldPassword;


    @Schema(
            description = "Новый пароль",
            example = "Test12345"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String newPassword;
}