package com.usyd.group08.elec5619.aop;

import com.usyd.group08.elec5619.models.User;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class UserTypeAspect {
    @Autowired
    HttpSession httpSession;
    @Before("@annotation(ValidateUserType)")
    public void validateUserType(JoinPoint joinPoint) {
        ValidateUserType validateUserType = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(ValidateUserType.class);
        User user = (User)httpSession.getAttribute("currentUser");
        List<String> roles = List.of(validateUserType.type().split(","));
        if(user == null || !roles.contains(user.getType())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
