package pl.lukk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;
import pl.lukk.repository.OfferRepository;
import pl.lukk.repository.UserRepository;

@Service
public class OfferServiceImpl implements OfferService
{
    @Autowired
    private OfferRepository offerRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void saveAddOffer(Offer offer, String ownerEmail)
    {
        User owner = userRepo.findByEmail(ownerEmail);
        offer.setPromoted(false);
        offer.setOwner(owner);
        offerRepo.save(offer);
    }

    @Override
    public void addPhoto(Long offerId, String ownerEmail, String photoFilename)
    {
        User owner = userRepo.findByEmail(ownerEmail);
        String photoPath = ("../uploads/" + photoFilename);
        //  offer from path must be own by user who want add photo:
        Offer databaseOffer = offerRepo.findByOwnerAndId(owner, offerId);

        try
        {
            List<String> photoPaths = databaseOffer.getPhotoPaths();
            photoPaths.add(photoPath);
            databaseOffer.setPhotoPaths(photoPaths);
        }
        catch (NullPointerException e)
        {
            List<String> photoPaths = new ArrayList<>();
            photoPaths.add(photoPath);
            databaseOffer.setPhotoPaths(photoPaths);
        }

        offerRepo.save(databaseOffer);
    }

    @Override
    public void deleteOffer(Long offerId, String ownerEmail)
    {
        User owner = userRepo.findByEmail(ownerEmail);
        Offer offerToDel = offerRepo.findByOwnerAndId(owner, offerId);

        if (offerToDel != null)
        {
            offerRepo.delete(offerToDel);
        }
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

        //        if (offer.getComment() != null)
        //        {
        //            databaseOffer.setComment(offer.getComment());
        //        }

        if (offer.getCountry() != null)
        {
            databaseOffer.setCountry(offer.getCountry());
        }

        if (offer.getCity() != null)
        {
            databaseOffer.setCity(offer.getCity());
        }

        //        if (offer.getUser() != null)
        //        {
        //            databaseOffer.setUser(offer.getUser());
        //        }

        databaseOffer.setPromoted(offer.isPromoted());

        offerRepo.save(databaseOffer);
    }

    @Override
    public Offer findByUserAndId(String ownerEmail, Long id)
    {

        return offerRepo.findByOwnerAndId(userRepo.findByEmail(ownerEmail), id);
    }

    @Override
    public Offer findOneById(Long id)
    {
        return offerRepo.findOne(id);
    }

    @Override
    public List<Offer> findByUserId(String userEmail)
    {
        User user = userRepo.findByEmail(userEmail);

        try
        {
            return offerRepo.findByOwnerId(user.getId());
        }
        catch (NullPointerException e)
        {
            return new ArrayList<>();
        }

    }
}
