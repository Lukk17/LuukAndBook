package pl.lukk.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import pl.lukk.entity.Message;
import pl.lukk.entity.User;
import pl.lukk.repository.MessageRepository;
import pl.lukk.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService
{
    @Autowired
    MessageRepository messageRepo;

    @Autowired
    UserRepository userRepo;
    
    private final JmsTemplate jmsTemplate = new JmsTemplate();

    @Override
    public Page<Message> findAll(Pageable pageable)
    {
        return messageRepo.findAll(pageable);
    }

    @Override
    public List<Message> findTop5ByOrderByCreated(User receiver)
    {
        return messageRepo.findTop5ByReceiverOrderByCreated(receiver);

    }

    @Override
    public Message findOneById(String email, Long messageId)
    {
        Message msg = messageRepo.findById(messageId).orElse(null);
        if (msg.getPermaReceiver().equals(userRepo.findByEmail(email)) || msg.getPermaSender().equals(
                userRepo.findByEmail(email)))
        {
            return msg;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void send(Message message, String senderEmail, Long receiverId)
    {
        //  entries which users can delete from mailbox:
        message.setReceiver(userRepo.findById(receiverId).orElse(null));
        message.setSender(userRepo.findByEmail(senderEmail));
        //  undeletable entries:
        message.setPermaReceiver(message.getReceiver());
        message.setPermaSender(message.getSender());

        message.setReaded(false);
        message.setCreated(LocalDateTime.now());
        messageRepo.save(message);
    }

    @Override
    public void readed(Long messageId, String receiverEmail)
    {
        Message message = messageRepo.findById(messageId).orElse(null);

        if (receiverEmail.equals(message.getReceiver().getEmail()))
        {
            message.setReaded(true);
        }

        messageRepo.save(message);
    }

    @Override
    public Integer unreadedNum(String receiverEmail)
    {

        List<Message> message = messageRepo.findAllByReadedAndReceiver(new Boolean(false),
                userRepo.findByEmail(receiverEmail));

        return message.size();
    }

    @Override
    public void remove(Long messageId, String userEmail)
    {
        Message message = messageRepo.findById(messageId).orElse(null);
        //  if user is sender, then set sender as null (delete from outbox)
        if (message.getSender().getEmail().equals(userEmail))
        {
            message.setSender(null);
            messageRepo.save(message);
        }
        //  if user is receiver, then set receiver as null (delete from inbox)
        if (message.getReceiver().getEmail().equals(userEmail))
        {
            message.setReceiver(null);
            messageRepo.save(message);
        }
    }

    @Override
    public Page<Message> findAllByWhereReceiverEmail(String email, Pageable pageable)
    {
        return messageRepo.findAllByReceiverEmail(email, pageable);
    }

    @Override
    public Page<Message> findAllByWhereSenderEmail(String email, Pageable pageable)
    {
        return messageRepo.findAllBySenderEmail(email, pageable);
    }
}
