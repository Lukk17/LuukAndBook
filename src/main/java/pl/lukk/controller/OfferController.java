package pl.lukk.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;
import pl.lukk.service.AvailabilityService;
import pl.lukk.service.OfferService;
import pl.lukk.service.UserService;

@Controller
@RequestMapping("/offer")
public class OfferController
{
    @Autowired
    AvailabilityService availabilityService;

    @Autowired
    OfferService offerService;
    
    @Autowired
    UserService userService;
    
    @GetMapping("/add")
    public String add(Model model)
    {
        Offer offer = new Offer();
        model.addAttribute("offer", offer);
        return "offer/add";
    }
    
    @PostMapping("/add")
    public String list(@Valid Offer offer)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByUserEmail(name);
        
//        offer.setOwner(user);
        offer.setPromoted(false);
        offerService.save(offer);
        return "offer/list";
    }
        

    @GetMapping("/{id}/details")
    public String list(Model model, @PathVariable(value = "id") Long id)
    {
        List<LocalDateTime> availableDates = availabilityService.findAvailableDatesByOffersId(id);
        Collections.sort(availableDates);                                                                               // sort by year

        
        model.addAttribute("availableDates", availableDates);
        
        
        //        
        //        
        //        
        //        LocalDateTime now = LocalDateTime.now();
        //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //        List<Integer> mon1 = new ArrayList<>();
        //
        //        List<Integer> days = new ArrayList<>();
        //        List<Integer> month = new ArrayList<>();
        //        List<Integer> year = new ArrayList<>();
        //
        //        List<LocalDateTime> m1 = new ArrayList<>();
        //        List<LocalDateTime> m2 = new ArrayList<>();
        //        List<LocalDateTime> m3 = new ArrayList<>();
        //        List<LocalDateTime> m4 = new ArrayList<>();
        //        List<LocalDateTime> m5 = new ArrayList<>();
        //        List<LocalDateTime> m6 = new ArrayList<>();
        //        List<LocalDateTime> m7 = new ArrayList<>();
        //        List<LocalDateTime> m8 = new ArrayList<>();
        //        List<LocalDateTime> m9 = new ArrayList<>();
        //        List<LocalDateTime> m10 = new ArrayList<>();
        //        List<LocalDateTime> m11 = new ArrayList<>();
        //        List<LocalDateTime> m12 = new ArrayList<>();
        //
        //       
        //
        //        for (LocalDateTime t : availableDates)
        //        {
        //days.add(t.getDayOfYear());
        //            days.add(t.getDayOfMonth());
        //            month.add(t.getMonthValue());
        //            year.add(t.getYear());
        //            if (t.getMonthValue() == 1)
        //            {
        //                m1.add(LocalDateTime.of(t.getYear(), t.getMonthValue(), t.getDayOfMonth(), t.getHour(), t.getMinute()));
        //                if (!mon1.contains(t.getYear()))
        //                {
        //                    mon1.add(t.getYear());                              // add year only once at beggining
        //                }
        //                if(!mon1.contains(t.getMonthValue()))
        //                {
        //                    mon1.add(t.getMonthValue());                        // add month only once at beggining of months days
        //                }
        //                mon1.add(t.getDayOfMonth());                            // save available days in january
        //            }
        //
        //        }
        //
        //        model.addAttribute("january", mon1);                            // list have first entry which is year and then free days

        return "views/offer/details";
    }
}
