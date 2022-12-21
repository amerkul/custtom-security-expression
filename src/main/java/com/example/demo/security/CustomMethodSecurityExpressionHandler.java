package com.example.demo.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        CustomMethodSecurityExpressionRoot securityExpressionRoot = new CustomMethodSecurityExpressionRoot(authentication);
        securityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
        securityExpressionRoot.setTrustResolver(this.trustResolver);
        securityExpressionRoot.setRoleHierarchy(getRoleHierarchy());
        return securityExpressionRoot;
    }

}
