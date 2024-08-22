package com.cm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cm.entity.Contact;
import com.cm.entity.User;
import com.cm.repository.ContactRepository;
import com.cm.services.ContactService;
import com.cm.services.UserService;

@Service
public class ContactServiceimpl implements ContactService {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactRepository contactRepo;

    @Override
    public Contact saveContact(Contact contact) {
        String UUID=java.util.UUID.randomUUID().toString();
        contact.setId(UUID);
        return contactRepo.save(contact);
    }

    @Override
    public List<Contact> getAllContact() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getContactById(String id) {
        // TODO Auto-generated method stub
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact with this id does not exist"));
    }

    @Override
    public Contact update(Contact contact) {
        Contact c1=contactRepo.findById(contact.getId()).orElseThrow(() -> new RuntimeException());
        
        c1.setAddress(contact.getAddress());
        c1.setDescription(contact.getAddress());
        c1.setEmail(contact.getEmail());
        c1.setFavourite(contact.isFavourite());
        c1.setLinkedlndLink(contact.getLinkedlndLink());
        c1.setName(contact.getName());
        c1.setPhoneNumber(contact.getPhoneNumber());
        c1.setPictures(contact.getPictures());
        c1.setUser(contact.getUser());
        c1.setWebsiteLink(contact.getWebsiteLink());
        
        contactRepo.save(c1);

        return contact;
    }

    @Override
    public void delete(String id) {
        contactRepo.deleteById(id);
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        // TODO Auto-generated method stub
        return contactRepo.findByUserId(userId);
    }

    @Override
    public Page<Contact> getByUser(User user,int page,int size,String direction,String sortBy) {
        // TODO Auto-generated method stub
        Sort sort=direction.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();

        var pageable=PageRequest.of(page, size, sort);

        return contactRepo.findByUser(user, pageable);      

    }

    @Override
    public Page<Contact> searchByName(String name, int size, int page, String sortBy, String order) {
        // TODO Auto-generated method stub
        Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable=PageRequest.of(page, size,sort);
        return contactRepo.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String email, int size, int page, String sortBy, String order) {
        // TODO Auto-generated method stub
        Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable=PageRequest.of(page, size,sort);
        return contactRepo.findByEmailContaining(email, pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumber, int size, int page, String sortBy, String order) {
        // TODO Auto-generated method stub
        Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable=PageRequest.of(page, size,sort);
        return contactRepo.findByPhoneNumberContaining(phoneNumber, pageable);
    }

   

}
