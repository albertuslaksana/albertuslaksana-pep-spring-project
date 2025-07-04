package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    @Query("SELECT m FROM Message m where m.messageId=?1")
    Message getMessageById(int id);

    @Query("SELECT m FROM Message m where m.postedBy=?1")
    List<Message> getMessagesByUser(int id);

    
}
