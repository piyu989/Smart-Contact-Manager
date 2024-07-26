package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.cm.entity.Contact;
import com.cm.entity.User;

public interface ContactRepository extends JpaRepository<Contact,String> {
    //find contact by user
    List<Contact> findByUser(User user);
    @Query("select c from contact c where c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId")String userId);
}
