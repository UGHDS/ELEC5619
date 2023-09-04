package com.usyd.group08.ELEC5619.aop;

import com.usyd.group08.ELEC5619.models.User;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class UserTypeAspect {
    @Autowired
    HttpSession httpSession;
    @Before("@annotation(ValidateUserType)")
    public void validateUserType(JoinPoint joinPoint) {
        ValidateUserType validateUserType = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(ValidateUserType.class);
        User user = (User)httpSession.getAttribute("currentUser");
        if(user == null || (!validateUserType.type().equals("any") && !user.getType().equals(validateUserType.type()))){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
