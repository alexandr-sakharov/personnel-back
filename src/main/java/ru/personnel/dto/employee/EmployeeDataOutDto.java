package ru.personnel.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные сотрудника")
public class EmployeeDataOutDto {


    @Schema(description = "Имя сотрудника")
    private String firstName;


    @Schema(description = "Фамилия сотрудника")
    private String lastName;


    @Schema(description = "Отчество сотрудника")
    private String patronymic;


    @Schema(description = "Данные паспорта")
    private String passport;


    @Schema(description = "ИНН сотрудника")
    private String inn;


    @Schema(description = "СНИЛС сотрудника")
    private String SNILS;


    @Schema(description = "Дата рождения")
    private String dateOfBirth;


    @Schema(description = "Адрес сотрудника")
    private String address;


    @Schema(description = "Почта сотрудника")
    private String mail;


    @Schema(description = "Телефон сотрудника")
    private String phone;


    @Schema(description = "Должность сотрудника")
    private String position;


    @Schema(description = "Дата приема")
    private String dateOfReceipt;


    @Schema(description = "Дата увольнения")
    private String dateOfDismissal;
}
