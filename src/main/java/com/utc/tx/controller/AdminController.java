package com.utc.tx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.utc.tx.model.Role;
import com.utc.tx.model.User;
import com.utc.tx.service.GenericService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
    @Autowired
    private MessageSource messageSource;
    
 	@Autowired(required = true)
    @Qualifier(value = "userService")
    private GenericService<User> userService;
    
    @Autowired(required = true)
    @Qualifier(value = "roleService")
    private GenericService<Role> roleService;
   

    @RequestMapping(value = "/list/user", method = RequestMethod.GET)
    public List<User> listUsers() {
        return this.userService.list();
    }
    
    @RequestMapping(value = "/list/role", method = RequestMethod.GET)
    public List<Role> listRole() {
        return this.roleService.list();
    }
    
    @RequestMapping(value = "/user/save", method = RequestMethod.PUT)
    public void save(@RequestBody User user) {
    	this.userService.add(user);
    }

}