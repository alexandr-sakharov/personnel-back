package ru.personnel.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "Данные для создания пользователя на основе заявки", title = "UserDto")
public class UserDto {
    @Schema(
            description = "Имя пользователя",
            required = true
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String username;


    @Schema(
            description = "Пароль",
            required = true
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String password;

}
