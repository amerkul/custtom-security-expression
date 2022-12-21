package com.example.demo.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if(authentication == null || targetDomainObject == null || !(permission instanceof String)) {
            return false;
        }
        String targetType = (String) targetDomainObject;
        return hasPrivilege(authentication, targetType.toUpperCase(), permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(),
                permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        for(GrantedAuthority authority : auth.getAuthorities()) {
            if(authority.getAuthority().startsWith(targetType) && authority.getAuthority().contains(permission)) {
                return true;
            }
        }
        return false;
    }

}
