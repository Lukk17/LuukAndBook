package pl.lukk.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BookedDate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private LocalDateTime bookedDate;

    @ManyToOne
    @JoinColumn(name = "bookedDate_offer")
    private Offer offer;
    
    @ManyToOne
    @JoinColumn(name = "bookedDate_user")
    private User user;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LocalDateTime getBookedDate()
    {
        return bookedDate;
    }

    public void setBookedDate(LocalDateTime bookedDate)
    {
        this.bookedDate = bookedDate;
    }

    public Offer getOffer()
    {
        return offer;
    }

    public void setOffer(Offer offer)
    {
        this.offer = offer;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
