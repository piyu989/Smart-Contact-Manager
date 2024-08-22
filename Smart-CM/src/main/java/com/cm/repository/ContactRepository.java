package com.cm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.cm.entity.Contact;
import com.cm.entity.User;

public interface ContactRepository extends JpaRepository<Contact,String> {
    //find contact by user
    Page<Contact> findByUser(User user,Pageable pageable);
    @Query("select c from contact c where c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId")String userId);

    Page<Contact> findByNameContaining(String namekeyword,Pageable pageable);
    Page<Contact> findByPhoneNumberContaining(String phoneNumber,Pageable pageable);
    Page<Contact> findByEmailContaining(String email,Pageable pageable);

}
