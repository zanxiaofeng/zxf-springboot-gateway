package zxf.springboot.authservicea.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/logon-failed")
    public ModelAndView logonFailed(HttpServletRequest request) {
        System.out.println("AuthController::logonFailed");
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView("logon-failed");
        modelAndView.addObject("SessionId", session.getId());
        return modelAndView;
    }

    @GetMapping("/logon-succeed")
    public ModelAndView logonSucceed(HttpServletRequest request) {
        System.out.println("AuthController::logonSucceed");
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView("logon-succeed");
        modelAndView.addObject("SessionId", session.getId());
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(HttpServletRequest request) {
        System.out.println("AuthController::home");
        HttpSession session = request.getSession(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("SessionId", session.getId());
        modelAndView.addObject("principal", (User)authentication.getPrincipal());
        return modelAndView;
    }
}
