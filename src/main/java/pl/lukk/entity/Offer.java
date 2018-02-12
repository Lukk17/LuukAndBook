package pl.lukk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "offer")
public class Offer
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "offer_owner")
    private Owner owner;
    
    @NotBlank
    @Size(max = 100)
    private String hotelName;
    
    @NotBlank
    private String roomNumber;
    
    @NotBlank
    @Min(value = 1)
    @Digits(integer = 1, fraction = 0)
    private Long roomCapacity;
    
    @NotBlank
    @Min(value = 1)
    @Digits(integer = 1, fraction = 2)
    private Double price;
    
    @Size(max = 3000)
    private String description;
    
    private boolean promoted;
    
    @ManyToOne
    @JoinColumn(name = "offer_user")
    private User user;
    
    @Size(max = 500)
    private String comment;
    
    @NotBlank
    private String country;
    
    @NotBlank
    private String city;
    

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName(String hotelName)
    {
        this.hotelName = hotelName;
    }

    public String getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public Long getRoomCapacity()
    {
        return roomCapacity;
    }

    public void setRoomCapacity(Long roomCapacity)
    {
        this.roomCapacity = roomCapacity;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isPromoted()
    {
        return promoted;
    }

    public void setPromoted(boolean promoted)
    {
        this.promoted = promoted;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
