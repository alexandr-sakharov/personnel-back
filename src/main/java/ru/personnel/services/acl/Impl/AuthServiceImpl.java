package ru.personnel.services.acl.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.personnel.dto.user.UserOutDto;
import ru.personnel.entity.RoleEntity;
import ru.personnel.entity.UserEntity;
import ru.personnel.mapper.UserMapper;
import ru.personnel.repository.UserRepository;
import ru.personnel.services.acl.AuthService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    /**
     * Авторизация пользователя
     *
     * @param username      инн пользователя
     * @param password пароль пользователя
     */
    @Override
    public UserOutDto loginAttempt(String username, String password) throws Exception {
        // Поиск пользователя по инн
        Optional<UserEntity> userEntityOpt = userRepository.findUserByUsername(username);

        // Если пользователь найден, то выполнить проверку пароля
        if (userEntityOpt.isPresent()) {
            // Получаем данные пользователя
            UserEntity user = userEntityOpt.get();
            List<RoleEntity> isAdminRole = user.getRoles().stream()
                    .filter(role -> Objects.equals(role.getRoleCode(), "system_administrator"))
                    .collect(Collectors.toList());

            // Если пароли совпадают, то выполнить аутентификацию пользователю
            if (user.getPassword().equals(password)) {
                UsernamePasswordAuthenticationToken authReq
                        = new UsernamePasswordAuthenticationToken(username, password);
                // Аутентификация пользователя с данными кредами
                Authentication auth = authManager.authenticate(authReq);

                // Получение контекста security
                SecurityContext sc = SecurityContextHolder.getContext();
                // Установка в контекст данной аутентификации
                sc.setAuthentication(auth);
            } else {
                throw new Exception("неверные данные учетной записи");
            }
            UserOutDto userOutDto = userMapper.toDto(user);
            userOutDto.setAdminMode(isAdminRole.size() == 1);
            return userOutDto;
        } else {
            throw new Exception("пользователь не найден");
        }
    }
}
