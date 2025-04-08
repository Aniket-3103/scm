package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(Model model) {
        System.out.println("I'm in contoller");

        // this model will be automatically passed to template and we can access it
        // their
        model.addAttribute("name", "Smart contact manager");

        // whatever string we're returning, html file of that name should be present in
        // templates
        return "home";
    }

    // about page
    @RequestMapping("/about")
    public String aboutPage() {
        return "about";
    }

    // service page
    @RequestMapping("/services")
    public String servicesPage(Model model) {
        model.addAttribute("isLogin", false);
        return "services";
    }

    // contact page
    @RequestMapping("/contacts")
    public String contactsPage() {
        return "contact";
    }

    // login page
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    // get register page
    @GetMapping("/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);
        return "register";
    }

    // register user

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute UserForm userForm) {
        // fetch the data with UserForm

        // validat the data
        


        // creating user class from userForm using builder method
        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .about(userForm.getAbout())
                .phoneNumber(userForm.getPhoneNumber())
                .profilePic("something")
                .build();
        

        // save to db with userService
        User savedUser=userService.saveUser(user);

        System.out.println("saved user: " + savedUser);
        // redirect to login page
        return "redirect:/login";
    }
}
