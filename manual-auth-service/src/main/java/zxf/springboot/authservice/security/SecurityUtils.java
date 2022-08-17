package zxf.springboot.authservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import zxf.springboot.authentication.MyAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

public class SecurityUtils {

    public static void logon(HttpServletRequest request, String username) {
        MyAuthentication myAuthentication = new MyAuthentication(new MyAuthentication.MyUser(username));
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(myAuthentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    public static MyAuthentication.MyUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (MyAuthentication.MyUser) authentication.getPrincipal();
    }

    public static void logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        session.invalidate();
    }
}
