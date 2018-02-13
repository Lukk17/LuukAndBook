package pl.lukk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.lukk.entity.Message;
import pl.lukk.entity.User;

public interface MessageService
{

    Page<Message> findAll(Pageable pageable);

    Page<Message> findAllByWhereReceiverEmail(String email, Pageable pageable);

    Page<Message> findAllByWhereSenderEmail(String email, Pageable pageable);

    void send(Message message, String senderEmail, Long receiverId);

    void remove(Long messageId, String userEmail);

    List<Message> findTop5ByOrderByCreated(User receiver);

}
