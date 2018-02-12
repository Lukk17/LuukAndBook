package pl.lukk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lukk.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>
{
    
}
