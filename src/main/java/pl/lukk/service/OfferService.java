package pl.lukk.service;

import java.util.List;

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

}
