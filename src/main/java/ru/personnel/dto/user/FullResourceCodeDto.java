package ru.personnel.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Права пользователя (ресурсы)", title = "FullResourceCodeDto")
public class FullResourceCodeDto {
    @Schema(description = "Код ресурса")
    private String resourceCode;

    @Schema(description = "Ресурс")
    private String accessKind;
}
