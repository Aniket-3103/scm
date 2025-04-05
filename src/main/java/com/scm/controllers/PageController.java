package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @RequestMapping("/")
    public String home(Model model){
        System.out.println("I'm in contoller");

        // this model will be automatically passed to template and we can access it their
        model.addAttribute("name", "Smart contact manager");

        //whatever string we're returning, html file of that name should be present in templates
        return "home";
    }

    //about page
    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    //service page
    @RequestMapping("/services")
    public String servicesPage(Model model){
        model.addAttribute("isLogin", false);
        return "services";
    }

    //contact page
    @RequestMapping("/contacts")
    public String contactsPage(){
        return "contact";
    }

    //login page
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    //register page
    @RequestMapping("/register")
    public String registerPage(){
        return "register";
    }
}
