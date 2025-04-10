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
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

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
    public String doRegister(@ModelAttribute UserForm userForm, HttpSession session) {
        // fetch the data with UserForm

        // validat the data

        // creating user class from userForm

        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("something")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("something");

        // save to db with userService
        User savedUser = userService.saveUser(user);

        // message for alerts
        Message message = Message.builder().type(MessageType.green).content("You're successfully registered!!!")
                .build();

        
        session.setAttribute("message", message);


        // redirect to login page
        return "redirect:/register";
    }
}
