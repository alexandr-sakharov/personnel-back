package ru.personnel.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Schema(description = "Данные при авторизации пользователя", title = "UserOutDto")
public class UserOutDto {
    @Schema(description = "id пользователя")
    private final Integer id;

    @Schema(description = "Имя пользователя")
    private final String username;

    @Schema(description = "Дата создания пользователя")
    private final LocalDateTime createdAt;

    @Schema(description = "Администратор")
    private boolean adminMode;
}
