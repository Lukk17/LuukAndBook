package pl.lukk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>
{
    Offer findByOwnerAndId(User owner, Long id);

    List<Offer> findByOwnerId(Long id);
    
    List<Offer> findByCountry(String country);
    
    List<Offer> findByCity(String city);
    
    List<Offer> findByHotelName(String hotelName);
    
    List<Offer> findByRoomCapacity(Long roomCapacity);
    
    @Query("select o from offer o join o.bookedDates bd where bd.user = ?1")
    List<Offer> findByBookedDatesUser(User user);
    
    List<Offer> findByOwnerEmail(String email);
    
    List<Offer> findByUserEmail(String email);
        
    List<Offer> findTop5ByOwner(User owner);
}
