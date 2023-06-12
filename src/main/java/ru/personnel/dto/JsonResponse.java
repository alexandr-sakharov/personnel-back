package ru.personnel.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Тело ответа", title = "JsonResponse")
public class JsonResponse {
    @Schema(description = "Код ответа")
    private String code;

    @Schema(description = "Сообщение ответа")
    private String message;
}