package ru.personnel.services.user.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.personnel.dto.user.UserDto;
import ru.personnel.dto.user.UserInDto;
import ru.personnel.entity.RoleEntity;
import ru.personnel.entity.UserEntity;
import ru.personnel.mapper.UserMapper;
import ru.personnel.repository.RoleRepository;
import ru.personnel.repository.UserRepository;
import ru.personnel.services.user.UserService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Создание пользователя на базе заявки
     * Проверка пользователя по ИНН
     * Присваивание роли пользователю
     *
     * @param userData данные пользователя
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserDto userData) throws Exception {
        try {
            UserEntity userEntity = UserMapper.instance.userDtoToUserEntity(userData);


            // Установка пользователю роли по умолчанию
            Optional<RoleEntity> defaultRoleOpt = roleRepository.findByDefaultRole();
            Set<RoleEntity> roles = new HashSet<>();
            userEntity.setRoles(roles);
            defaultRoleOpt.ifPresent(roles::add);

            userEntity.setCreatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
            userRepository.save(userEntity);
        } catch (
                Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    /**
     * Получения доступа роли по инн
     *
     * @param username Инн пользователя
     */
    @Override
    public Boolean getAccessUser(String username) {
        SecurityContext sc = SecurityContextHolder.getContext();
        String userInn = sc.getAuthentication().getPrincipal().toString();
        Boolean isAdmin = userIsAdmin(userInn);
        if(isAdmin) return true;
        return Objects.equals(username, userInn);
    }

    /**
     * Проверка пользователя на администратора
     *
     * @param username Инн пользователя
     */
    public Boolean userIsAdmin(String username) {
        Optional<UserEntity> userEntityOpt = userRepository.findUserByUsername(username);
        // Если пользователь найден, то выполнить проверку пароля
        if (userEntityOpt.isPresent()) {
            // Получаем данные пользователя
            UserEntity user = userEntityOpt.get();
            List<RoleEntity> isAdminRole = user.getRoles().stream()
                    .filter(role -> Objects.equals(role.getRoleCode(), "system_administrator"))
                    .collect(Collectors.toList());
            return isAdminRole.size() == 1;
        } else {
            return false;
        }
    }

    /**
     * Изменение данных пользователя
     *
     * @param updateUserData обновленные данные пользователя
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserInDto updateUserData) throws Exception {
        try {
            // Получаем пользователя
            UserEntity userInDB = userRepository.findUserByUsername(updateUserData.getOldUsername())
                    .orElseThrow(() -> new Exception("пользователь не найден"));

            if (!Objects.equals(updateUserData.getOldPassword(), userInDB.getPassword())) {
                throw new Exception("неверный пароль");
            }

            // Установка нового пароля
            if (updateUserData.getNewPassword() != null) {
                userInDB.setPassword(updateUserData.getNewPassword());
            }
            userRepository.save(userInDB);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
