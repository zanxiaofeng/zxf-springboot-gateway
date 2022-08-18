package zxf.springboot.authservice.rest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zxf.springboot.authservice.security.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final String HTTP_HEADER_NAME_X_E2E_Trust_Token = "X-E2E-Trust-Token";
    private static final String SESSION_ATTRIBUTE_ACCESS_TOKEN = "Access-Token";
    private static final String SESSION_ATTRIBUTE_RETURN_PAGE = "Return-Page";
    private static final String MODEL_AND_VIEW_OBJECT_KEY_SESSION_ID = "SessionId";
    private static final String MODEL_AND_VIEW_OBJECT_KEY_SITE_URL = "siteUrl";
    private static final String MODEL_AND_VIEW_OBJECT_KEY_PRINCIPAL = "principal";

    @Value("${site.url}")
    private String siteUrl;

    @GetMapping("/logon")
    public ModelAndView logon(HttpServletRequest request, @RequestParam(required = false) String returnPage) {
        HttpSession session = request.getSession(true);
        logInfo("logon", request, session);

        session.setAttribute(SESSION_ATTRIBUTE_RETURN_PAGE, returnPage);

        ModelAndView modelAndView = new ModelAndView("logon-page");
        modelAndView.addObject(MODEL_AND_VIEW_OBJECT_KEY_SESSION_ID, session.getId());
        modelAndView.addObject(MODEL_AND_VIEW_OBJECT_KEY_SITE_URL, siteUrl);
        return modelAndView;
    }

    @PostMapping("/logon-form")
    public ModelAndView logonForm(HttpServletRequest request, HttpSession session, @RequestParam String name, @RequestParam String passwd) {
        logInfo("logon-form", request, session);

        if (!"davis".equals(name) || !"davis".equals(passwd)) {
            return new ModelAndView("redirect:" + siteUrl + "/auth/logon-failed");
            //throw new AccessDeniedException("Invalid username or password!");
        }

        SecurityUtils.logon(session, name);

        String returnPage = (String) session.getAttribute(SESSION_ATTRIBUTE_RETURN_PAGE);
        if (Strings.isNotEmpty(returnPage)) {
            return new ModelAndView("redirect:" + returnPage);
        }

        return new ModelAndView("redirect:" + siteUrl + "/auth/logon-succeed");
    }

    @GetMapping("/logon-failed")
    public ModelAndView logonFailed(HttpServletRequest request, HttpSession session) {
        logInfo("logon-failed", request, session);

        ModelAndView modelAndView = new ModelAndView("logon-failed-page");
        modelAndView.addObject(MODEL_AND_VIEW_OBJECT_KEY_SESSION_ID, session.getId());
        modelAndView.addObject(MODEL_AND_VIEW_OBJECT_KEY_SITE_URL, siteUrl);
        return modelAndView;
    }

    @GetMapping("/logon-succeed")
    public ModelAndView logonSucceed(HttpServletRequest request, HttpSession session) {
        logInfo("logon-succeed", request, session);

        ModelAndView modelAndView = new ModelAndView("logon-succeed-page");
        modelAndView.addObject(MODEL_AND_VIEW_OBJECT_KEY_SESSION_ID, session.getId());
        modelAndView.addObject(MODEL_AND_VIEW_OBJECT_KEY_SITE_URL, siteUrl);
        modelAndView.addObject(MODEL_AND_VIEW_OBJECT_KEY_PRINCIPAL, SecurityUtils.getCurrentUser());
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException {
        logInfo("logout", request, session);

        SecurityUtils.logout(request, response);

        return new ModelAndView("redirect:" + siteUrl + "/profile/home");
    }

    private void logInfo(String method, HttpServletRequest request, HttpSession session) {
        String e2eTrustToken = request.getHeader(HTTP_HEADER_NAME_X_E2E_Trust_Token);
        System.out.println("AuthController::" + method + ", " + session.getId() + ", " + e2eTrustToken);
        session.setAttribute(SESSION_ATTRIBUTE_ACCESS_TOKEN, session.getId() + "-" + method);
    }
}
