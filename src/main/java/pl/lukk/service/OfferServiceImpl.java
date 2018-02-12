package pl.lukk.service;

import org.springframework.stereotype.Service;

import pl.lukk.entity.Offer;
import pl.lukk.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService
{
    private OfferRepository offerRepo;
    
    @Override
    public void save(Offer offer)
    {
        offerRepo.save(offer);
    }
    
}
