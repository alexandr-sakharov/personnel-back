package ru.personnel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные пагинации", title = "PaginationDataDto")
public class PaginationDataDto {
    @Schema(description = "Номер страницы",
            example = "0"
    )
    private Integer pageNumber;

    @Schema(
            description = "Элементов на странице",
            example = "100",
            minimum = "1")
    private Integer countOnPage;

    @Schema(description = "Количество элементов")
    private Integer totalCount;
}
