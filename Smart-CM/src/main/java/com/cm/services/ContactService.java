package com.cm.services;

import java.util.List;
import com.cm.entity.Contact;
import com.cm.entity.User;

public interface ContactService {
    Contact saveContact(Contact contact);
    List<Contact> getAllContact();
    Contact getContactById(String id);
    Contact update(Contact contact);
    void delete(String id);
    List<Contact> searchUser(String name,String email,String phoneNumber);
    List<Contact> getByUserId(String userId);
    List<Contact> getByUser(User user);
}
