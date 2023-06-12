package ru.personnel.services.employee;

import ru.personnel.dto.employee.EmployeeDataInDto;
import ru.personnel.dto.employee.EmployeeDataOutDto;

import java.util.List;

public interface EmployeeService {

    /**
     * Получение данных всех сотрудников
     */
    List<EmployeeDataOutDto> getEmployees();

    /**
     * Добавление нового сотрудника
     * @param employeeData данные сотрудника
     */
    void addNewEmployee(EmployeeDataInDto employeeData) throws Exception;

    /**
     * Обновление данных сотрудника
     * @param employeeData обновленные данные сотрудника
     */
    EmployeeDataOutDto updateEmployee(EmployeeDataInDto employeeData);

    /**
     * Увольнение сотрудника
     * @param inn ИНН сотрудника
     */
    void dismissal(String inn);
}
