package ru.personnel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Получение всех заявок", title = "RequestsDataWithPagination")
public class RequestsDataWithPagination<T> {
    @Schema(description = "Данные заявок")
    private T requests;
    @Schema(description = "Данные пагинации нашего ответа")
    private PaginationDataDto paginationData;
}
