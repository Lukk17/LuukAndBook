package pl.lukk.service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lukk.entity.Offer;
import pl.lukk.repository.AvailabilityRepository;
import pl.lukk.repository.OfferRepository;

@Service
public class AvailabilityServiceImpl implements AvailabilityService
{
    @Autowired
    AvailabilityRepository avialabilityRepo;
    
    @Autowired
    OfferRepository offerRepo;
    
    @Override
    public List<LocalDateTime> findAvailableDatesByOffersId(Long id)
    {
        Offer offer = offerRepo.findOne(id);
        List<LocalDateTime> bookedDates = avialabilityRepo.findBookedDatesByOffers(offer);
        
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusMonths(11);
        
        List<LocalDateTime> allDays = new ArrayList<>();
        
        long days = now.until( end, ChronoUnit.DAYS);
        
        for (int i = 0; i < days ; i++)
        {
            allDays.add(now.plusDays(i));
        }
        
        List<LocalDateTime> available = new ArrayList<>();
        
        for (int i = 0; i < allDays.size(); i++)
        {
            if(!allDays.get(i).equals(bookedDates.get(i)))
            {
                available.add(allDays.get(i));
            }
            
        }
        
        
        return available;
    }
}
