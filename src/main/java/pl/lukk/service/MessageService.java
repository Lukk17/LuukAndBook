package pl.lukk.service;

import java.util.List;

import javax.jms.JMSException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.lukk.entity.Message;
import pl.lukk.entity.User;

public interface MessageService
{

    /**
     * Give all of Messages entity entries from database.
     * Divide them into pages.
     * 
     * @param pageable      Parameter needed for dividing list of Messages into pages.
     * @return              Return paged list of Messages.         
     */
    Page<Message> findAll(Pageable pageable);

    /**
     * Give all of Messages entity entries from database,
     * where receiver has same email as given.
     * 
     * @param email         Email of messages receiver.
     * @param pageable      Number of page, which will be given.
     * @return              Return paged list of Messages for given receiver.
     */
    Page<Message> findAllByWhereReceiverEmail(String email, Pageable pageable);

    /**
     * Give all of Messages entity entries from database,
     * where sender has same email as given.
     * 
     * @param email         Email of messages sender.
     * @param pageable      Number of page, which will be given.
     * @return              Return paged list of Messages for given sender.
     */
    Page<Message> findAllByWhereSenderEmail(String email, Pageable pageable);

    /**
     * Send message of given text to given receiver.
     * 
     * @param message           Text of message.
     * @param senderEmail       Mail of sender.
     * @param receiverId        ID of message receiver.
     */
    void send(Message message, String senderEmail, Long receiverId);

    /**
     * Remove message with given ID. 
     * User email confirm authorization for deleting.
     * Message will be still visible to sender/receiver.
     * 
     * @param messageId     ID of message to delete.
     * @param userEmail     Email of user who delete message.
     */
    void remove(Long messageId, String userEmail);

    /**
     * Give last five Messages of given receiver. 
     * 
     * @param receiver      Receiver of messages.
     * @return              Return list of Messages (List<Message>)
     */
    List<Message> findTop5ByOrderByCreated(User receiver);

    /**
     * Give Message by given ID, if it belong to given user.
     * 
     * @param email         Email of logged user.
     * @param messageId     ID of message.
     * @return              Return one Message of given ID.
     */
    Message findOneById(String email, Long messageId);

    /**
     * Set readed attribute of given Message to true,
     * if given user is receiver.
     * 
     * @param messageId         ID of Message.
     * @param receiverEmail     Email of message receiver.
     */
    void readed(Long messageId, String receiverEmail);

    /**
     * Give number of user's unreaded messages.
     * 
     * @param receiverEmail     User email.
     * @return                  Return number of unreaded messages.
     */
    Integer unreadedNum(String receiverEmail);

    void publish(String message, String adminEmail) throws JMSException;

    void getTopicMessage(String userEmail) throws JMSException;

}
