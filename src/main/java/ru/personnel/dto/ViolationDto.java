package ru.personnel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "Тело ошибки валидации", title = "ViolationDto")
public class ViolationDto {
    @Schema(description = "Имя поля")
    private final String fieldName;

    @Schema(description = "Сообщение валидации данного поля")
    private final String message;
}
