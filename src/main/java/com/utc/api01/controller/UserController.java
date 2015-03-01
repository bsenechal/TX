package com.utc.api01.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.utc.api01.mail.ApplicationMailer;
import com.utc.api01.model.Role;
import com.utc.api01.model.User;
import com.utc.api01.service.GeneriqueService;

@Controller
public class UserController {
    private static final String JSP_USER = "user";
    private static final String REDIRECT_EDITUSER = "editUser";
    private static final String REDIRECT_ACCUEIL = "index";
    private static final String REDIRECT_VIEWUSER = "viewUser";
    private static final String REDIRECT_LISTUSERS = "listUsers";
    private static final String LIST_ROLE = "listRole";
    private static final String MSG_ADD_SUCCESS = "L'utilisateur a correctement été ajouté.";
    private static final String MSG_EDIT_SUCCESS = "L'utilisateur a correctement été modifié.";
    private static final String MSG_SUPPR_SUCCESS = "L'utilisateur a correctement été supprimé.";
    private GeneriqueService<User> userService;
    private GeneriqueService<Role> roleService;
    private ApplicationContext applicationContext;
    
    @Autowired(required = true)
    @Qualifier(value = "applicationContext")
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(GeneriqueService<User> us) {
        this.userService = us;
    }

    @Autowired(required = true)
    @Qualifier(value = "roleService")
    public void setRoleService(GeneriqueService<Role> r) {
        this.roleService = r;
    }

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute(REDIRECT_LISTUSERS, this.userService.list());
        return JSP_USER;
    }

    @RequestMapping(value = "/admin/user/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute(LIST_ROLE, roleService.list());
        return REDIRECT_EDITUSER;
    }

    @RequestMapping("/admin/user/remove/{idUser}")
    public ModelAndView removeUser(@PathVariable("idUser") int id) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", MSG_SUPPR_SUCCESS);
        model.setViewName(JSP_USER);
        this.userService.remove(id);
        model.addObject(REDIRECT_LISTUSERS, this.userService.list());
        return model;
    }

    @RequestMapping("/admin/user/edit/{idUser}")
    public String editUser(@PathVariable("idUser") int idUser, Model model) {
        User u = this.userService.getById(idUser);
        model.addAttribute("user", u);
        model.addAttribute(LIST_ROLE, roleService.list());
        return REDIRECT_EDITUSER;
    }
    
    @RequestMapping("/user/found/{nameUser}")
    public String foundUser(@PathVariable("nameUser") String nameUser, Model model) {
        User u = this.userService.getByCriteria("firstname", nameUser);
        if(u != null){
            model.addAttribute("user", u);
            return REDIRECT_VIEWUSER;
        }else return REDIRECT_ACCUEIL;
    }
    
    @RequestMapping(value = "/avatarDisplayById/{idUser}", method = RequestMethod.GET)
    public void displayAvatar(@PathVariable("idUser") int idUser, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(this.userService.getById(idUser).getAvatar());
        response.getOutputStream().close();
    }
    
    @RequestMapping(value = "/avatarDisplay/", method = RequestMethod.GET)
    public void showAvatar(HttpServletResponse response) throws IOException {
        String userName;
    
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User){
            userName = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(this.userService.getByCriteria("email", userName).getAvatar());
        response.getOutputStream().close();
        }
    }
    
    @RequestMapping(value = "/admin/user/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("user") User u, @RequestParam("file") MultipartFile file, BindingResult result) throws IOException {
        ModelAndView model = new ModelAndView();
       
        if (result.hasErrors()) {
            if (u.getIdUser() != 0){
                model.addObject("user", u);
            }
            model.addObject(LIST_ROLE, roleService.list());
            model.setViewName(REDIRECT_EDITUSER);
        } else {
            model.setViewName(JSP_USER);
            u.setRole(this.roleService.getById(u.getRoleUser()));
            
            if (u.getIdUser() != 0) {
                model.addObject("msg", MSG_EDIT_SUCCESS);
                 
                //Get the mailer instance
                ApplicationMailer mailer = (ApplicationMailer) applicationContext.getBean("mailService");
         
                //Send a pre-configured mail
                String newligne=System.getProperty("line.separator"); 
                mailer.sendPreConfiguredMail("Mise à jour de votre compte " + u.getFirstname() 
                        + newligne + "Votre identifiant : " + u.getEmail() 
                        + newligne + "Votre mot de passe : " + u.getPassword()
                        , u.getEmail());
                
                if (u.getAvatar() == null){
                    u.setAvatar(this.userService.getById(u.getIdUser()).getAvatar());
                }
                
                this.userService.update(u);
            } else {
                u.setCreationDate(new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
                model.addObject("msg", MSG_ADD_SUCCESS);
                
              //Get the mailer instance
                ApplicationMailer mailer = (ApplicationMailer) applicationContext.getBean("mailService");
         
                //Send a pre-configured mail
                String newligne=System.getProperty("line.separator"); 
                mailer.sendPreConfiguredMail("Bienvenue sur The Book " + u.getFirstname() 
                        + newligne + "Votre identifiant : " + u.getEmail() 
                        + newligne + "Votre mot de passe : " + u.getPassword()
                        , u.getEmail());
                u.setAvatar(file.getBytes());
                this.userService.add(u);
            }
            model.addObject(REDIRECT_LISTUSERS, this.userService.list());
        }
        return model;
    }
    
    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws ServletException {
            binder.registerCustomEditor(byte[].class,
                new ByteArrayMultipartFileEditor());
    }
    
}
