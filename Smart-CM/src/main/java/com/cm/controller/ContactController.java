package com.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.entity.Contact;
import com.cm.form.ContactForm;
import com.cm.services.ContactService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/add")
    public String add(Model model){
        
        ContactForm contactForm=new ContactForm(); 
        contactForm.setFavourite(true);
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }

    @PostMapping("/add")
    public String saveContact(@ModelAttribute ContactForm contactForm) {
        //TODO: process POST request
        System.out.println(contactForm);

        Contact contact=new Contact();
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setEmail(contactForm.getEmail());
        contact.setFavourite(contactForm.isFavourite());
        contact.setLinkedlndLink(contactForm.getLinkedInLink());
        contact.setName(contactForm.getName());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        // contact.setPictures(contactForm.getProfileImage());

        contactService.saveContact(contact);
        
        return "redirect:/user/contacts/add";
    }
    
}
