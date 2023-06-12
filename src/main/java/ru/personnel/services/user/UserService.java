package ru.personnel.services.user;

import ru.personnel.dto.user.UserDto;
import ru.personnel.dto.user.UserInDto;

public interface UserService {
    /**
     * Создание пользователя на базе заявки
     *
     * @param userData данные пользователя
     */
    void saveUser(UserDto userData) throws Exception;

    /**
     * Изменение данных пользователя
     *
     * @param updateUserData обновленные данные пользователя
     */
    void updateUser(UserInDto updateUserData) throws Exception;

    /**
     * Получения доступа роли по имени пользователя
     *
     * @param username имя пользователя
     */
    Boolean getAccessUser(String username);
}
