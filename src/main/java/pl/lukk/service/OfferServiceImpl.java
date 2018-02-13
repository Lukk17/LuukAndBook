package pl.lukk.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<Offer> search(Offer offer)
    {
        
        List<Offer> result = new ArrayList<>();
        List<Offer> byCity = null;
        List<Offer> byCountry = null;
        List<Offer> byHotelName = null;
        List<Offer> byRoomCapacity = null;
        
        Set<Offer> setRes = new HashSet<>();
        try
        {
            byCity = offerRepo.findByCity(offer.getCity());

        }
        catch (NullPointerException e)
        {
        }
        try
        {
            byCountry = offerRepo.findByCountry(offer.getCountry());

            if (byCountry != null)
            {
                for (Offer o : byCountry)
                {
                    result.add(o);
                }
            }
        }
        catch (NullPointerException e)
        {
        }
        try
        {
            byHotelName = offerRepo.findByHotelName(offer.getHotelName());

            if (byHotelName != null)
            {
                for (Offer o : byHotelName)
                {
                    result.add(o);
                }
            }
        }
        catch (NullPointerException e)
        {
        }
        try
        {
            byRoomCapacity = offerRepo.findByRoomCapacity(offer.getRoomCapacity());

            if (byRoomCapacity != null)
            {
                for (Offer o : byRoomCapacity)
                {
                    result.add(o);
                }
            }
        }
        catch (NullPointerException e)
        {
        }
        
        if (byCity != null)
        {
            for (Offer a : byCity)
            {
                if (byCountry != null)
                {
                    for (Offer b : byCountry)
                    {
                        if (b.equals(a))
                        {
                            if (byHotelName != null)
                            {
                                for (Offer c : byHotelName)
                                {
                                    if (c.equals(b))
                                    {
                                        if (byRoomCapacity != null)
                                        {
                                            for (Offer d : byRoomCapacity)
                                            {
                                                if (d.equals(c))
                                                {
                                                    result.add(d);
                                                    setRes.add(d);
                                                }
                                            }
                                        }

                                    }

                                }
                            }
                            else
                            {
                                if (byRoomCapacity != null)
                                {
                                    for (Offer c : byRoomCapacity)
                                    {
                                        if (c.equals(b))
                                        {
                                            result.add(c);
                                            setRes.add(c);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    if (byHotelName != null)
                    {
                        for (Offer b : byHotelName)
                        {
                            if (b.equals(a))
                            {
                                if (byRoomCapacity != null)
                                {
                                    for (Offer c : byRoomCapacity)
                                    {
                                        if (c.equals(b))
                                        {
                                            result.add(c);
                                            setRes.add(c);
                                        }
                                    }
                                }

                            }

                        }
                    }
                    else
                    {
                        if (byRoomCapacity != null)
                        {
                            for (Offer b : byRoomCapacity)
                            {
                                if (b.equals(a))
                                {
                                    result.add(b);
                                    setRes.add(b);
                                }
                            }
                        }
                    }

                }
            }
        }
        else
        {
            if (byCountry != null)
            {
                for (Offer a : byCountry)
                {

                    if (byHotelName != null)
                    {
                        for (Offer b : byHotelName)
                        {
                            if (b.equals(a))
                            {
                                if (byRoomCapacity != null)
                                {
                                    for (Offer c : byRoomCapacity)
                                    {
                                        if (c.equals(b))
                                        {
                                            result.add(c);
                                            setRes.add(c);
                                        }
                                    }
                                }

                            }

                        }
                    }
                    else
                    {
                        if (byRoomCapacity != null)
                        {
                            for (Offer b : byRoomCapacity)
                            {
                                if (b.equals(a))
                                {
                                    result.add(b);
                                    setRes.add(b);
                                }
                            }
                        }
                    }

                }
            }
            else
            {
                if (byHotelName != null)
                {
                    for (Offer a : byHotelName)
                    {

                        if (byRoomCapacity != null)
                        {
                            for (Offer b : byRoomCapacity)
                            {
                                if (b.equals(a))
                                {
                                    result.add(b);
                                    setRes.add(b);
                                }
                            }
                        }

                    }
                }
                else
                {
                    if (byRoomCapacity != null)
                    {
                        for (Offer a : byRoomCapacity)
                        {

                            result.add(a);
                            setRes.add(a);

                        }
                    }
                }

            }
        }
        List <Offer> temp = new ArrayList<>();
        
        for(Offer o : setRes)
        {
            temp.add(o);
        }

        return temp;
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
