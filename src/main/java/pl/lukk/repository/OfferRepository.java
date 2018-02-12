package pl.lukk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>
{
    Offer findByOwnerAndId(User owner, Long id);

    List<Offer> findByOwnerId(Long Id);
}
