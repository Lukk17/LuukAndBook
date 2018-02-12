package pl.lukk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;
import pl.lukk.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService
{
    @Autowired
    private OfferRepository offerRepo;
    

    @Override
    public void saveAddOffer(Offer offer, User owner)
    {
        offer.setPromoted(false);
        offer.setOwner(owner);
        offerRepo.save(offer);
    }
    
    @Override
    public void addPhoto(Offer offer, User owner, String photoFilename)
    {
        String photoPath = ("src/main/resources/static/uploads/"+photoFilename);
    //  offer from path must be own by user who want add photo:
        Offer databaseOffer = offerRepo.findByOwnerAndId(owner, offer.getId());
        
        
        if(databaseOffer.getPhotoPaths()!=null)
        {
            List<String> photoPaths = databaseOffer.getPhotoPaths();
            photoPaths.add(photoPath);
            offer.setPhotoPaths(photoPaths);
        }
        else
        {
            List<String> photoPaths = new ArrayList<>();
            photoPaths.add(photoPath);
            offer.setPhotoPaths(photoPaths);
        }
                
        offerRepo.save(offer);
    }

    @Override
    public void deleteOffer(Long offerId, User owner)
    {
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
    public Offer findByUserAndId(User owner, Long id)
    {
        return offerRepo.findByOwnerAndId(owner, id);
    }

    @Override
    public Offer findOneById(Long id)
    {
        return offerRepo.findOne(id);
    }

    @Override
    public List<Offer> findByUserId(Long id)
    {

        return offerRepo.findByOwnerId(id);
    }
}
