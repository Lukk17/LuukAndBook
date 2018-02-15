package pl.lukk.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank
    private String password;

    private boolean enabled;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String photoPath;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToMany
    @JsonIgnore
    private List<Offer> offers;

    @OneToMany
    @JsonIgnore
    private List<Message> receivedMessage;

    @OneToMany
    @JsonIgnore
    private List<Message> sentMessage;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<BookedDate> bookedDates;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getPhotoPath()
    {
        return photoPath;
    }

    public void setPhotoPath(String photoPath)
    {
        this.photoPath = photoPath;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public List<Offer> getOffers()
    {
        return offers;
    }

    public void setOffers(List<Offer> offers)
    {
        this.offers = offers;
    }

    public List<Message> getReceivedMessage()
    {
        return receivedMessage;
    }

    public void setReceivedMessage(List<Message> receivedMessage)
    {
        this.receivedMessage = receivedMessage;
    }

    public List<Message> getSentMessage()
    {
        return sentMessage;
    }

    public void setSentMessage(List<Message> sentMessage)
    {
        this.sentMessage = sentMessage;
    }

    public List<BookedDate> getBookedDates()
    {
        return bookedDates;
    }

    public void setBookedDates(List<BookedDate> bookedDates)
    {
        this.bookedDates = bookedDates;
    }
}
