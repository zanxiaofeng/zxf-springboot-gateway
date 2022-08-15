package zxf.springboot.authservice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zxf.springboot.authentication.MyAuthentication;
import zxf.springboot.authservice.security.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${site.url}")
    private String siteUrl;

    @GetMapping("/logon")
    public ModelAndView logon(HttpServletRequest request) {
        System.out.println("AuthController::logon");
        HttpSession session = request.getSession(true);
        ModelAndView modelAndView = new ModelAndView("logon-page");
        modelAndView.addObject("SessionId", session.getId());
        modelAndView.addObject("siteUrl", siteUrl);
        return modelAndView;
    }

    @PostMapping("/logon-form")
    public ModelAndView logonForm(HttpServletRequest request, @RequestParam String name, @RequestParam String passwd) {
        System.out.println("AuthController::logonForm");
        if (!"davis".equals(name) || !"davis".equals(passwd)) {
            return new ModelAndView("redirect:" + siteUrl + "/auth/logon-failed");
            //throw new AccessDeniedException("Invalid username or password!");
        }
        MyAuthentication myAuthentication = new MyAuthentication(new MyAuthentication.MyUser(name));
        SecurityUtils.logon(request, myAuthentication);
        HttpSession session = request.getSession(false);
        session.setAttribute("AccessToken", "token-zzz");
        return new ModelAndView("redirect:" + siteUrl + "/auth/logon-succeed");
    }

    @GetMapping("/logon-failed")
    public ModelAndView logonFailed(HttpServletRequest request) {
        System.out.println("AuthController::logonFailed");
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView("logon-failed-page");
        modelAndView.addObject("SessionId", session.getId());
        modelAndView.addObject("siteUrl", siteUrl);
        return modelAndView;
    }

    @GetMapping("/logon-succeed")
    public ModelAndView logonSucceed(HttpServletRequest request) {
        System.out.println("AuthController::logonSucceed");
        HttpSession session = request.getSession(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("logon-succeed-page");
        modelAndView.addObject("SessionId", session.getId());
        modelAndView.addObject("siteUrl", siteUrl);
        modelAndView.addObject("principal", (MyAuthentication.MyUser) authentication.getPrincipal());
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        System.out.println("AuthController::logout");
        SecurityUtils.logout(request);
        return new ModelAndView("redirect:" + siteUrl + "/profile/home");
    }
}
