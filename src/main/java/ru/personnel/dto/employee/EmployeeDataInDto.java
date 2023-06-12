package ru.personnel.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "Данные для создания нового сотрудника")
public class EmployeeDataInDto {


    @Schema(description = "Имя сотрудника")
    @NotBlank(message = "Значение обязательно к заполнению")
    private String firstName;


    @Schema(description = "Фамилия сотрудника")
    @NotBlank(message = "Значение обязательно к заполнению")
    private String lastName;


    @Schema(description = "Отчество сотрудника")
    @NotBlank(message = "Значение обязательно к заполнению")
    private String patronymic;


    @Schema(description = "Данные паспорта")
    @Pattern(
            regexp = "^(\\d){10}$",
            message = "Не соответствует формату ИНН (Физ.л.)"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String passport;


    @Schema(description = "ИНН сотрудника")
    @Pattern(
            regexp = "^(\\d){12}$",
            message = "Не соответствует формату ИНН (Физ.л.)"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String inn;


    @Schema(description = "СНИЛС сотрудника")
    @Pattern(
            regexp = "^(\\d){11}$",
            message = "Не соответствует формату ИНН (Физ.л.)"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String SNILS;


    @Schema(description = "Дата рождения")
    @Pattern(
            regexp = "^\\d{2}.\\d{2}.\\d{4}$",
            message = "Не соответствует формату даты"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String dateOfBirth;


    @Schema(description = "Адрес сотрудника")
    @NotBlank(message = "Значение обязательно к заполнению")
    private String address;


    @Schema(description = "Почта сотрудника")
    @Pattern(
            regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$",
            message = "Не соответствует формату ИНН (Физ.л.)"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String mail;


    @Schema(description = "Телефон сотрудника")
    @Pattern(
            regexp = "^(\\d){12}$",
            message = "Не соответствует формату ИНН (Физ.л.)"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String phone;


    @Schema(description = "Должность сотрудника")
    @NotBlank(message = "Значение обязательно к заполнению")
    private String position;


    @Schema(description = "Дата приема")
    @Pattern(
            regexp = "^\\d{2}.\\d{2}.\\d{4}$",
            message = "Не соответствует формату даты"
    )
    @NotBlank(message = "Значение обязательно к заполнению")
    private String dateOfReceipt;


    @Schema(description = "Дата увольнения")
    @Pattern(
            regexp = "^$|^\\d{2}.\\d{2}.\\d{4}$",
            message = "Не соответствует формату даты"
    )
    private String dateOfDismissal;
}
