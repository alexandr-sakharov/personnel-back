package ru.personnel.controllers.employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.personnel.constants.ResponseConstants;
import ru.personnel.dto.JsonResponse;
import ru.personnel.dto.JsonResponseWithData;
import ru.personnel.dto.employee.EmployeeDataInDto;
import ru.personnel.dto.employee.EmployeeDataOutDto;
import ru.personnel.services.employee.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Employee", description = "Сотрудник")
@RequestMapping(value = "/employee")
public class EmployeeController {
    EmployeeService employeeService;

    /**
     * Получение всех сотрудников
     */
    @Operation(description = "Получение сотрудников")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('administrator', 'read')")
    @ResponseBody
    public JsonResponseWithData<List<EmployeeDataOutDto>> getEmployees() {
        try {
            List<EmployeeDataOutDto> employeesData = employeeService.getEmployees();
            return new JsonResponseWithData<>(ResponseConstants.Code.OK, "Данные сотрудников", employeesData);
        } catch (Exception e) {
            return new JsonResponseWithData<>(ResponseConstants.Code.ERR, "Ошибка получения данных сотрудников: " + e.getMessage(), null);
        }
    }

    /**
     * Добавление сотрудника
     *
     * @param employeeData данные сотрудника
     */
    @Operation(description = "Добавление сотрудника")
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('administrator', 'read')")
    @ResponseBody
    public JsonResponse addEmployee(@RequestBody EmployeeDataInDto employeeData) {
        try {
            employeeService.addNewEmployee(employeeData);
            return new JsonResponse(ResponseConstants.Code.OK, "Сотрудник добавлен");
        } catch (Exception e) {
            return new JsonResponse(ResponseConstants.Code.ERR, "Ошибка добавления сотрудника: " + e.getMessage());
        }
    }

    /**
     * Обновление данных сотрудника
     *
     * @param employeeData обновленные данные сотрудника
     */
    @Operation(description = "Обновление данных сотрудника")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('administrator', 'read')")
    @ResponseBody
    public JsonResponseWithData<EmployeeDataOutDto> updateEmployee(@RequestBody EmployeeDataInDto employeeData) {
        try {
            EmployeeDataOutDto updateEmployeeData = employeeService.updateEmployee(employeeData);
            return new JsonResponseWithData<>(ResponseConstants.Code.OK, "Данные сотрудника обновлены", updateEmployeeData);
        } catch (Exception e) {
            return new JsonResponseWithData<>(ResponseConstants.Code.ERR, "Ошибка обновления данных: " + e.getMessage(), null);
        }
    }

    /**
     * Увольнение сотрудника
     *
     * @param inn ИНН сотрудника
     */
    @Operation(description = "Увольнение сотрудника по inn", parameters = {
            @Parameter(name = "inn", description = "ИНН сотрудника"),
    })
    @GetMapping(value = "/dismissal/{inn}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('administrator', 'read')")
    @ResponseBody
    public JsonResponse dismissalEmployee(
            @Valid
            @PathVariable("inn")
            @Pattern(
                    regexp = "^(\\d){12}$",
                    message = "Не соответствует формату ИНН"
            )
            String inn) {
        try {
            employeeService.dismissal(inn);
            return new JsonResponse(ResponseConstants.Code.OK, "Сотрудник уволен");
        } catch (Exception e) {
            return new JsonResponse(ResponseConstants.Code.ERR, "Ошибка увольнения сотрудника " + e.getMessage());
        }
    }
}
