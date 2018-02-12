package pl.lukk.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "offer")
public class Offer
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_owner")
    private User owner;

    @NotBlank
    @Size(max = 100)
    private String hotelName;

    @NotBlank
    private String roomNumber;

    @NotNull
    @Min(value = 1)
    private Long roomCapacity;

    @NotNull
    @Min(value = 1)
    private Double price;

    @Size(max = 3000)
    private String description;

    private boolean      promoted;
    
    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> photoPaths;

    @ManyToOne
    @JoinColumn(name = "offer_user")
    private User user;

    @Size(max = 500)
    private String comment;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    public List<String> getPhotoPaths()
    {
        return photoPaths;
    }

    public void setPhotoPaths(List<String> photoPaths)
    {
        this.photoPaths = photoPaths;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
}
