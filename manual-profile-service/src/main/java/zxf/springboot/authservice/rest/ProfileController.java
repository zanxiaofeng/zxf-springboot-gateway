package zxf.springboot.authservice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zxf.springboot.authentication.MyAuthentication;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Value("${site.url}")
    private String siteUrl;

    @GetMapping("/home")
    public ModelAndView home(HttpServletRequest request) {
        System.out.println("AuthController::home, E2E-Trust-Token=" + request.getHeader("X-E2E-Trust-Token"));
        HttpSession session = request.getSession(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("home-page");
        modelAndView.addObject("SessionId", session.getId());
        modelAndView.addObject("siteUrl", siteUrl);
        modelAndView.addObject("principal", (MyAuthentication.MyUser) authentication.getPrincipal());
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpServletRequest request) {
        System.out.println("AuthController::profile");
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView("profile-page");
        modelAndView.addObject("SessionId", session.getId());
        modelAndView.addObject("siteUrl", siteUrl);
        return modelAndView;
    }

    @PostMapping("/profile-form")
    public ModelAndView profileForm(HttpServletRequest request, @RequestParam Integer age) {
        System.out.println("AuthController::profileForm");
        HttpSession session = request.getSession(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyAuthentication.MyUser myUser = (MyAuthentication.MyUser) authentication.getPrincipal();
        myUser.setAge(age);
        return new ModelAndView("redirect:" + siteUrl + "/profile/home");
    }
}
