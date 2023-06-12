package ru.personnel.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "Данные для авторизации", title = "UserLoginDto")
public class UserLoginDto {
    @Schema(
            description = "Имя пользователя",
            example = "admin"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String username;

    @Schema(
            description = "Пароль",
            example = "Admin1234"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String password;
}
