package ru.personnel.services.employee.Impl;

import ru.personnel.dto.employee.EmployeeDataInDto;
import ru.personnel.dto.employee.EmployeeDataOutDto;
import ru.personnel.dto.user.UserDto;
import ru.personnel.services.employee.EmployeeService;
import ru.personnel.services.user.UserService;
import ru.personnel.utils.HashUtils;
import ru.personnel.utils.PasswordUtils;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    UserService userService;


    /**
     * Получение данных всех сотрудников
     */
    @Override
    public List<EmployeeDataOutDto> getEmployees() {
        return null;
    }


    /**
     * Добавление нового сотрудника
     *
     * @param employeeData данные сотрудника
     */
    @Override
    public void addNewEmployee(EmployeeDataInDto employeeData) throws Exception {
        try {
            UserDto userData = new UserDto();
            String newPassword = PasswordUtils.generatePassword();
            userData.setPassword(HashUtils.getMD5Hash(newPassword).toLowerCase());
            userData.setUsername(employeeData.getInn());
            userService.saveUser(userData);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
     * Обновление данных сотрудника
     *
     * @param employeeData обновленные данные сотрудника
     */
    @Override
    public EmployeeDataOutDto updateEmployee(EmployeeDataInDto employeeData) {
        return null;
    }

    /**
     * Увольнение сотрудника
     *
     * @param inn ИНН сотрудника
     */
    @Override
    public void dismissal(String inn) {

    }
}
