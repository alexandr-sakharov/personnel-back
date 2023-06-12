package ru.personnel.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Права пользователя", title = "UserPermissionsDto")
public class UserPermissionsDto {
    private Integer id;

    @Schema(description = "имя пользователя")
    private String username;

    @Schema(description = "Ресурсы пользователя")
    private List<FullResourceCodeDto> resourceCodeList;
}
