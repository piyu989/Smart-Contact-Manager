package com.cm.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cm.entity.Contact;
import com.cm.entity.User;

public interface ContactService {
    Contact saveContact(Contact contact);
    List<Contact> getAllContact();
    Contact getContactById(String id);
    Contact update(Contact contact);
    void delete(String id);

    Page<Contact> searchByName(String name, int size,int page,String sortBy,String orderBy);
    Page<Contact> searchByEmail(String email, int size,int page,String sortBy,String orderBy);
    Page<Contact> searchByPhoneNumber(String phoneNumber,int size,int page,String sortBy,String orderBy);

    List<Contact> getByUserId(String userId);
    Page<Contact> getByUser(User user,int page,int size,String direction,String sortBy);
}
