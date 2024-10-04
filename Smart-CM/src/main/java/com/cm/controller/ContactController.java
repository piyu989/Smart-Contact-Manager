package com.cm.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cm.entity.Contact;
import com.cm.entity.User;
import com.cm.form.ContactForm;
import com.cm.helper.AppConstants;
import com.cm.helper.Helper;
import com.cm.helper.Message;
import com.cm.helper.MessageType;
import com.cm.services.ContactService;
import com.cm.services.ImageService;
import com.cm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger=org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    
    @Autowired
    private UserService userService;
    
    @GetMapping("/add")
    public String add(Model model){ 
        
        ContactForm contactForm=new ContactForm(); 
        contactForm.setFavourite(true);
        model.addAttribute("contactForm", contactForm);
        
        return "user/add_contact";
    }
    
    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult result, Authentication authentication,HttpSession session) {
        //TODO: process POST request
        
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> logger.info(error.toString()));
            session.setAttribute("message", 
            Message.builder().
            content("fill details properly").
            type(MessageType.red).
            build());
            return "user/add_contact";
        }
        // System.out.println(contactForm);
        logger.info("file informatino {}"+ contactForm.getContactImage().getOriginalFilename());
        
        String fileURL=imageService.uploadImage(contactForm.getContactImage());
        String username=Helper.getEmailOfLoggedInUser(authentication);
        User user=userService.getUserByEmail(username);

        Contact contact=new Contact();
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setEmail(contactForm.getEmail());
        contact.setFavourite(contactForm.isFavourite());
        contact.setLinkedlndLink(contactForm.getLinkedInLink());
        contact.setName(contactForm.getName());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setUser(user);
        contact.setPictures(fileURL);

        contactService.saveContact(contact);
        
        // System.out.println("ram ram ram ram ram");

        session.setAttribute("message", Message.builder().content("Contact Added Succesfully").type(MessageType.green).build());
        
        return "redirect:/user/contacts/add";
    }
    

    @GetMapping("/view")
    public String viewContact(
    @RequestParam(value="page",defaultValue = "0") int page,
    @RequestParam(value = "size",defaultValue = "3") int size,    
    @RequestParam(value = "direction",defaultValue = "asc") String direction,    
    @RequestParam(value="sortBy",defaultValue = "email") String sortBy,
    Authentication authentication, Model model) {

        String username=Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact>pageContacts = contactService.getByUser(user,page,size,direction,sortBy);

        model.addAttribute("pageContacts", pageContacts);


        return "user/contacts";
    }

    @GetMapping("/search")
    public String getMethodName(
        @RequestParam("field") String field,
        @RequestParam("keyword") String keyword,
        @RequestParam(value = "page",defaultValue = "0")int page,
        @RequestParam(value = "size",defaultValue = AppConstants.PAGE_SIZE)int size,
        @RequestParam(value = "sortBy",defaultValue = "name")String sortBy,
        @RequestParam(value = "direction",defaultValue = "asc")String direction,
        Model model


    ) {
        // System.out.println("keyword,+field"+field+" "+keyword);
        // logger.info("keyword, field", field, keyword);

        // Page<Contact> pageContact=null;

        // if(field.equalsIgnoreCase("name")){
        //     pageContact=contactService.searchByName(keyword, size, page, sortBy, direction);
        // }
        // else if(field.equalsIgnoreCase("email")){
        //     pageContact=contactService.searchByName(keyword, size, page, sortBy, direction);
        // }else if(field.equalsIgnoreCase("phoneNumber")){
        //     pageContact=contactService.searchByPhoneNumber(keyword, size, page, sortBy, direction);
        // }

        // System.out.println(pageContact.toString());

        // model.addAttribute("pageContact", pageContact);

        return "user/search";
    }
    
    

}
