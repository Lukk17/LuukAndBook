package pl.lukk.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lukk.entity.BookedDate;
import pl.lukk.entity.Offer;
import pl.lukk.entity.User;
import pl.lukk.repository.BookedDateRepository;
import pl.lukk.repository.OfferRepository;

@Service
public class BookedDateServiceImpl implements BookedDateService
{
    @Autowired
    BookedDateRepository bookedRepo;

    @Autowired
    OfferRepository offerRepo;
    
    @Autowired
    UserService userService;
    
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public List<BookedDate> findAll()
    {
        return bookedRepo.findAll();
    }

    @Override
    public List<BookedDate> findAllBookedDatesByOffer(Long offerId)
    {
        return bookedRepo.findAllByOffer(offerRepo.findOne(offerId));
    }

    @Override
    public List<String> findAvailableDatesByOffersId(Long offerId)
    {
        List<BookedDate> bookedList = bookedRepo.findAllByOffer(offerRepo.findOne(offerId));

        List<LocalDate> bookedDates = new ArrayList<>();
        List<LocalDate> availableDays = new ArrayList<>();
        List<String> availableDates = new ArrayList<>();
        
        LocalDate now = LocalDate.now();
        LocalDate end = now.plusMonths(11);
        long days = now.until(end, ChronoUnit.DAYS);

        for (BookedDate d : bookedList)
        {
            bookedDates.add(d.getBookedDate());
        }

        for (int i = 0; i < days; i++)
        {
            availableDays.add(now.plusDays(i));
        }
        
        if (!bookedDates.isEmpty())
        {
            
            for (int i = 0; i < bookedDates.size(); i++)
            {
                for (int j = 0; j < availableDays.size(); j++)
                {
                    if (availableDays.get(j).equals(bookedDates.get(i)))
                    {
                        availableDays.remove(j);
                    }
                }
                
            }
        }

        for (LocalDate t : availableDays)
        {
            availableDates.add(t.format(FORMATTER));
        }

        return availableDates;
    }
    
    @Override
    public void bookDates(List<String> datesToBookList, Long offerId, String userEmail)
    {
        Offer offer = offerRepo.findOne(offerId);
        User user = userService.findByUserEmail(userEmail);
        
        for(String s: datesToBookList)
        {
            BookedDate newBooked = new BookedDate();
            newBooked.setBookedDate(LocalDate.parse(s, FORMATTER));
            newBooked.setOffer(offer);
            newBooked.setUser(user);
            bookedRepo.save(newBooked);
        }
        
    }
}
