package ru.personnel.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.personnel.constants.ResponseConstants;
import ru.personnel.dto.JsonResponse;
import ru.personnel.dto.JsonResponseWithData;
import ru.personnel.dto.user.UserLoginDto;
import ru.personnel.dto.user.UserOutDto;
import ru.personnel.services.acl.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Авторизация")
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * Авторизация пользователя
     *
     * @param userDataLogin данные для авторизации
     */
    @Operation(description = "Вход в систему")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonResponseWithData<UserOutDto> login(HttpServletRequest request, @RequestBody UserLoginDto userDataLogin) {
        try {
            UserOutDto userData = authService.loginAttempt(userDataLogin.getUsername(), userDataLogin.getPassword());
            SecurityContext sc = SecurityContextHolder.getContext();
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            return new JsonResponseWithData<>(ResponseConstants.Code.OK, "Успешный вход в систему", userData);
        } catch (Exception e) {
            return new JsonResponseWithData<>(ResponseConstants.Code.ERR, "Ошибка входа в систему: " + e.getMessage(), null);
        }
    }

    /**
     * Выход из системы
     */
    @Operation(description = "Выход из системы")
    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse logout(final HttpServletRequest request, final HttpServletResponse response) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return new JsonResponse(ResponseConstants.Code.OK, "Сессия завершена");
        } catch (Exception e) {
            return new JsonResponse(ResponseConstants.Code.ERR, "Ошибка завершения сессии");
        }
    }
}
