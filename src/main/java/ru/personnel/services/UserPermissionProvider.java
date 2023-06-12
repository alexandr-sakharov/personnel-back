package ru.personnel.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.personnel.dto.user.FullResourceCodeDto;
import ru.personnel.dto.user.UserPermissionsDto;
import ru.personnel.entity.UserEntity;
import ru.personnel.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPermissionProvider {

    private final UserRepository userRepository;

    /**
     * Получение доступов для пользователя
     *
     * @param username инн пользователя
     */
    public UserPermissionsDto getUserPermissions(String username) {
        UserPermissionsDto dto = new UserPermissionsDto();

        // Получение пользователя по инн
        Optional<UserEntity> _userEntityOpt = userRepository.findUserByUsername(username);
        if (_userEntityOpt.isPresent()){
            // Получаем пользователя
            UserEntity user = _userEntityOpt.get();

            // Получения ролей пользователя
            List<FullResourceCodeDto> permissionList = user.getRoles()
                    .stream()
                    .flatMap(role -> role.getRolesResourceList()
                            .stream()
                            .map(e -> new FullResourceCodeDto(e.getResource().getResourceCode(), e.getResource().getResourceCode()))
                    )
                    .collect(Collectors.toList());
            // Запись прав, id, инн
            dto.setResourceCodeList(permissionList);
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
        }
        return dto;
    }
}
