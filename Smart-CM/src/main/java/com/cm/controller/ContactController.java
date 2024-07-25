package com.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    @RequestMapping("/add")
    public String add(){
        return "user/add_contact";
    }
}