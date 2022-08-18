package zxf.springboot.authservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import zxf.springboot.authentication.MyAuthentication;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public static void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        //Basic Logout
        new SecurityContextLogoutHandler().logout(request, response, null);

        //Cookie Clearing Logout
        for (Cookie cookie: request.getCookies()) {
            Cookie cookieToDelete = new Cookie(cookie.getName(), null);
            cookieToDelete.setMaxAge(0);
            response.addCookie(cookieToDelete);
        }

        //Clear-Site-Data Header Logout
        new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL)
                .writeHeaders(request, response);

        //Logout on Request
        request.logout();
    }
}
