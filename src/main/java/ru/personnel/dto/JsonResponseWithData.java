package ru.personnel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Тело ответа с данными", title = "JsonResponseWithData")
public class JsonResponseWithData<T> extends JsonResponse {
    @Schema(description = "Данные нашего ответа")
    private T data;
    public JsonResponseWithData(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }
}
