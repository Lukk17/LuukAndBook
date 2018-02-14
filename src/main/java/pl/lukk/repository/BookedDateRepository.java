package pl.lukk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lukk.entity.BookedDate;
import pl.lukk.entity.Offer;

@Repository
public interface BookedDateRepository extends JpaRepository<BookedDate, Long>
{
    List<BookedDate> findAllByOffer(Offer offer);
}
