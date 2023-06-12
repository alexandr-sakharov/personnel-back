package ru.personnel.services.acl;

import ru.personnel.dto.user.UserOutDto;

public interface AuthService {

    /**
     * Авторизация пользователя
     *
     * @param username      имя пользователя
     * @param password пароль пользователя
     */
    UserOutDto loginAttempt(String username, String password) throws Exception;
}
