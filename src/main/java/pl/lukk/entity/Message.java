package pl.lukk.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "message")
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String text;
    
    @ManyToOne
    @JoinColumn(name = "message_sender")
    private User sender;
    
    
    @ManyToOne
    @JoinColumn(name = "message_receiver")
    private User receiver;
    
    //  perma sender and receiver to save who send/receive even if sender/receiver delete it from mailbox
    @ManyToOne
    @JoinColumn(name = "message_permaSender")
    private User permaSender;
    
    
    @ManyToOne
    @JoinColumn(name = "message_permaReceiver")
    private User permaReceiver;
    
    private LocalDateTime created;

    
    
    public User getPermaSender()
    {
        return permaSender;
    }

    public void setPermaSender(User permaSender)
    {
        this.permaSender = permaSender;
    }

    public User getPermaReceiver()
    {
        return permaReceiver;
    }

    public void setPermaReceiver(User permaReceiver)
    {
        this.permaReceiver = permaReceiver;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated(LocalDateTime created)
    {
        this.created = created;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public User getSender()
    {
        return sender;
    }

    public void setSender(User sender)
    {
        this.sender = sender;
    }

    public User getReceiver()
    {
        return receiver;
    }

    public void setReceiver(User receiver)
    {
        this.receiver = receiver;
    }
}
