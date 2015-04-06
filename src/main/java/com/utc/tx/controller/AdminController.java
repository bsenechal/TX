package com.utc.tx.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.utc.tx.model.Role;
import com.utc.tx.model.User;
import com.utc.tx.service.GenericService;

@Controller
public class AdminController {
	private static final String JSP_ADMIN_MAIN = "admin/adminMain";
	private static final String JSP_ADMIN_USER = "admin/adminUser";
    private static final String REDIRECT_EDITUSER = "editUser";
    private static final String LISTUSERS = "listUsers";
    private static final String LIST_ROLE = "listRole";
	
    @Autowired
    private MessageSource messageSource;
    
 	@Autowired(required = true)
    @Qualifier(value = "userService")
    private GenericService<User> userService;
    
    @Autowired(required = true)
    @Qualifier(value = "roleService")
    private GenericService<Role> roleService;
   

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();

        model.setViewName(JSP_ADMIN_MAIN);
        return model;
    }
	
    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute(LISTUSERS, this.userService.list());
        return JSP_ADMIN_USER;
    }

    @RequestMapping(value = "/admin/user/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute(LIST_ROLE, roleService.list());
        return REDIRECT_EDITUSER;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/admin/user/remove/{idUser}")
    public ModelAndView removeUser(@PathVariable("idUser") int id, Locale locale) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", messageSource.getMessage("USER_DELETE_SUCCESS", null , locale));
        
        model.setViewName(JSP_ADMIN_USER);
        this.userService.remove(id);
        model.addObject(LISTUSERS, this.userService.list());
        return model;
    }

    @RequestMapping("/admin/user/edit/{idUser}")
    public String editUser(@PathVariable("idUser") int idUser, Model model) {
        User u = this.userService.getById(idUser);
        model.addAttribute("user", u);
        model.addAttribute(LIST_ROLE, roleService.list());
        return REDIRECT_EDITUSER;
    }
}