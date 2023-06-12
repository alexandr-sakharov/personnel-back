package ru.personnel.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.personnel.constants.AclConstants;
import ru.personnel.dto.user.UserPermissionsDto;
import ru.personnel.entity.RoleEntity;
import ru.personnel.entity.RolesResourceEntity;
import ru.personnel.entity.UserEntity;
import ru.personnel.repository.RolesResourceRepository;
import ru.personnel.repository.UserRepository;
import ru.personnel.services.UserPermissionProvider;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public class ResourceAuthorityDescriptor implements GrantedAuthority{
        protected String name;
        protected String accessKind;

        ResourceAuthorityDescriptor(String name, String accessKind){
            this.name = name;
            this.accessKind = accessKind;
        }

        @Override
        public String getAuthority() {
            return name + ":" + accessKind;
        }
    }

    final private UserPermissionProvider userPermissionProvider;
    final private UserRepository userRepository;
    final private RolesResourceRepository rolesResourceRepository;

    public Map<String, String> getAuthorities(String username) {
        Optional<UserEntity> _userOpt = userRepository.findUserByUsername(username);
        if (_userOpt.isEmpty()) return null;
        UserEntity user = _userOpt.get();
        Set<RoleEntity> roles = user.getRoles();

        Map<String, String> authorities = new HashMap<>();

        for (var role : roles) {
            List<RolesResourceEntity> rolesResourceList = rolesResourceRepository.findAllByRole_Id(role.getId());
            for (var roleResource : rolesResourceList) {
                String resourceCode = roleResource.getResource().getResourceCode();
                String accessKind = roleResource.getAccessKind();
                if (!authorities.containsKey(resourceCode)) {
                    // Если текущая роль отсутствует, добавляем
                    authorities.put(resourceCode, accessKind);
                } else if (AclConstants.ResourceAccessKind.READ.getAccessCode().equals(authorities.get(resourceCode)) &&
                        AclConstants.ResourceAccessKind.WRITE.getAccessCode().equals(accessKind)) {
                    // Если у пользователя есть ресурсы на ro и rw устанавливаем права на rw
                    authorities.put(resourceCode, AclConstants.ResourceAccessKind.WRITE.getAccessCode());
                }
            }
        }

        return authorities;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String userName = authentication.getName();
        UserPermissionsDto userPermissions = userPermissionProvider.getUserPermissions(userName);
        String password = authentication.getCredentials().toString();

        Map<String, String> authorities = getAuthorities(authentication.getName());
        List<ResourceAuthorityDescriptor> resourceList = new ArrayList<>();
        if (userPermissions.getResourceCodeList() != null){
            resourceList = userPermissions.getResourceCodeList()
                    .stream()
                    .map(e -> new ResourceAuthorityDescriptor(e.getResourceCode(), authorities.get(e.getResourceCode())))
                    .collect(Collectors.toList());
        }

        return new UsernamePasswordAuthenticationToken(
                userName, password, resourceList);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
