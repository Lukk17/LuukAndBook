package pl.lukk.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lukk.entity.Message;
import pl.lukk.entity.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>
{
  
    List<Message> findTop5ByReceiverOrderByCreated(User receiver);
    
    List<Message> findAllByReadedAndReceiver(Boolean readed, User receiver);
    
    Page<Message> findAllByReceiverEmail(String email, Pageable pageable);
    
    Page<Message> findAllBySenderEmail(String email, Pageable pageable);
}
