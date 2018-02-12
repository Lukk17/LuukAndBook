package pl.lukk.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.lukk.entity.Availability;
import pl.lukk.entity.Offer;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long>
{
    @Query("select a.bookedDates from Availability a where offers=?1")
    List<LocalDateTime> findBookedDatesByOffers(@Param("1")Offer offer); 
}
