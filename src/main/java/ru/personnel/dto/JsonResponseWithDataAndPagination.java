package ru.personnel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Тело ответа с данными", title = "JsonResponseWithDataAndPagination")
public class JsonResponseWithDataAndPagination<T> extends JsonResponse {
    @Schema(description = "Данные нашего ответа")
    private T data;
    @Schema(description = "Данные пагинации нашего ответа")
    private PaginationDataDto paginationData;
    public JsonResponseWithDataAndPagination(String code, String message, T data, PaginationDataDto paginationData) {
        super(code, message);
        this.data = data;
        this.paginationData = paginationData;
    }
}
