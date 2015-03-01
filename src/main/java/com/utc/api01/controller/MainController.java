package com.utc.api01.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.utc.api01.model.Book;
import com.utc.api01.model.User;
import com.utc.api01.service.GeneriqueService;

@Controller
public class MainController {
    private GeneriqueService<Book> bookService;
    private GeneriqueService<User> userService;
    private static final String MSG_DECONNECTION = "Vous avez correctement été déconnecté.";
    private static final String JSP_MONCOMPTE = "monCompte";

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(GeneriqueService<Book> us) {
        this.bookService = us;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(GeneriqueService<User> us) {
        this.userService = us;
    }
    
    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public String defaultPage(Model model) {
        model.addAttribute("listBook", this.bookService.list());
        return "index";
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();

        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/monCompte", method = RequestMethod.GET)
    public ModelAndView monCompte() {
        ModelAndView m = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User){
            String userName = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            m.addObject("user", this.userService.getByCriteria("email", userName));
        }
        m.setViewName(JSP_MONCOMPTE);
        return m;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error",
                    getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", MSG_DECONNECTION);
        }
        model.setViewName("login");

        return model;
    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession()
                .getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "L'adresse email n'est pas correcte";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "L'adresse email et le mot de passe de correspondent pas !";
        }

        return error;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

}