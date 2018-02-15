package pl.lukk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;

public interface OfferService
{
    Offer findByUserAndId(String ownerEmail, Long id);

    Offer findOneById(Long id);

    void saveEditOffer(Offer offer);

    List<Offer> findByUserId(String userEmail);

    void deleteOffer(Long offerId, String ownerEmail);

    void addPhoto(Long offerId, String ownerEmail, String photoFilename);

    void saveAddOffer(Offer offer, String ownerEmail);

    List<Offer> search(Offer offer);

    List<Offer> findByBookedDatesUser(String email);

    Page<Offer> findALL(Pageable pageable);

    Integer userOfferNum(String userEmail);

    Integer ownerOfferNum(String ownerEmail);


    List<Offer> findTop5ByUser(String userEmail);

    List<Offer> findTop5ByOwner(String ownerEmail);

}
