package com.loyalty.api.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "API Gateway. Username: " + user.getUsername();
    }

    /**
     * Override default login page with simple help message
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String help() {
        return "Please provide username and password as JSON object {\"username\": ..., \"password\": ...}";
    }
}
