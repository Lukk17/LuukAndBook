package pl.lukk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;
import pl.lukk.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService
{
    private OfferRepository offerRepo;

    @Override
    public void saveAddOffer(Offer offer, User owner)
    {
        offer.setPromoted(false);                                   //  admin can change
        offer.setOwner(owner);                                      //  safer to set owner here(from spring security session), not to give this choice to user or send in hidden parameter in form
        offerRepo.save(offer);
    }

    @Override
    public void saveEditOffer(Offer offer)
    {
        Offer databaseOffer = offerRepo.findOne(offer.getId());

        if (offer.getId() != null)
        {
            databaseOffer.setId(offer.getId());
        }

        if (offer.getOwner() != null)
        {
            databaseOffer.setOwner(offer.getOwner());
        }

        if (offer.getHotelName() != null)
        {
            databaseOffer.setHotelName(offer.getHotelName());
        }

        if (offer.getRoomNumber() != null)
        {
            databaseOffer.setRoomNumber(offer.getRoomNumber());
        }

        if (offer.getRoomCapacity() != null)
        {
            databaseOffer.setRoomCapacity(offer.getRoomCapacity());
        }

        if (offer.getPrice() != null)
        {
            databaseOffer.setPrice(offer.getPrice());
        }

        if (offer.getDescription() != null)
        {
            databaseOffer.setDescription(offer.getDescription());
        }

        if (offer.getComment() != null)
        {
            databaseOffer.setComment(offer.getComment());
        }

        if (offer.getCountry() != null)
        {
            databaseOffer.setCountry(offer.getCountry());
        }

        if (offer.getCity() != null)
        {
            databaseOffer.setCity(offer.getCity());
        }

        if (offer.getUser() != null)
        {
            databaseOffer.setUser(offer.getUser());
        }

        databaseOffer.setPromoted(offer.isPromoted());

        offerRepo.save(databaseOffer);
    }

    @Override
    public Offer findByUserAndId(User owner, Long id)
    {
        return offerRepo.findByUserAndId(owner, id);
    }

    @Override
    public Offer findOneById(Long id)
    {
        return offerRepo.findOne(id);
    }

    @Override
    public List<Offer> findByUserId(Long id)
    {

        return offerRepo.findByUserId(id);
    }
}
