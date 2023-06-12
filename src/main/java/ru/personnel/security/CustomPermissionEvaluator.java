package ru.personnel.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.personnel.constants.AclConstants;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(
            Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }

        return hasPrivilege(auth, targetDomainObject.toString(), permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(),
                permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String target, String permission) {
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            String[] resourceDescriptor = grantedAuth.getAuthority().split(":");
            if (resourceDescriptor[0].equals(target)){
                if (resourceDescriptor[1].equals(AclConstants.ResourceAccessKind.WRITE.getAccessCode())){
                    return true;
                }
                else if ((resourceDescriptor[1].equals(AclConstants.ResourceAccessKind.READ.getAccessCode())) &&
                        (permission.equals(AclConstants.ResourceAccess.READ.getAccess()))){
                    return true;
                }
            }
        }
        return false;
    }
}
