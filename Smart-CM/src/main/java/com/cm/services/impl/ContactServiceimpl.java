package com.cm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.entity.Contact;
import com.cm.repository.ContactRepository;
import com.cm.services.ContactService;

@Service
public class ContactServiceimpl implements ContactService {

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
    public List<Contact> searchUser(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchUser'");
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        // TODO Auto-generated method stub
        return contactRepo.findByUserId(userId);
    }
    
}
