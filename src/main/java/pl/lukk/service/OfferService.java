package pl.lukk.service;

import java.util.List;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;

public interface OfferService
{
    Offer findByUserAndId(User owner, Long id);

    void saveAddOffer(Offer offer, User owner);

    Offer findOneById(Long id);

    void saveEditOffer(Offer offer);

    List<Offer> findByUserId(Long id);

    void deleteOffer(Long offerId, User owner);

    void addPhoto(Long offerId, User owner, String photoFilename);

}
