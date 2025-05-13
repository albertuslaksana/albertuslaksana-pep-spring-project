package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService{
    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(AccountRepository accountRepository, MessageRepository messageRepository){
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    public Message makeMessage(Message message){
        if(message.getMessageText().isBlank() || message.getMessageText().length() > 255 || !accountRepository.existsById(message.getPostedBy())){
            return null;
        }
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int id){
        return messageRepository.getMessageById(id);
    }

    public int deleteMessage(int messageId) {
        int result = 0;
        if(messageRepository.existsById(messageId)){
            messageRepository.deleteById(messageId);
            result = 1;
            return result;
        }
        return result;
    }

    public int updateMessage(int messageId, Message message){
        int result = 1;
        if(!messageRepository.existsById(messageId) || message.getMessageText().isBlank() || message.getMessageText().length() > 255){
            result = 0;
            return result;
        }
        messageRepository.getById(messageId).setMessageText(message.getMessageText());
        return result;
    } 

    public List<Message> getAllMessagesFromUser(int accountId){
        return messageRepository.getMessagesByUser(accountId);
    }



    
    
}
