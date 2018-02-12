package pl.lukk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;
import pl.lukk.service.OfferService;
import pl.lukk.service.StorageService;
import pl.lukk.service.UserService;

@Controller
@RequestMapping("/offer")
public class OfferController
{

    @Autowired
    OfferService offerService;

    @Autowired
    UserService userService;

    @Autowired
    StorageService storageService;

    @GetMapping("/owner/add")
    public String add(Model model)
    {
        Offer offer = new Offer();
        model.addAttribute("offer", offer);
        return "views/offer/add";
    }

    @PostMapping("/owner/add")
    public String add(@Valid Offer offer, BindingResult bresult, Authentication auth)
    {
        if (bresult.hasErrors())
        {
            return "/views/offer/add";
        }
        else
        {
            String email = auth.getName();
            User owner = userService.findByUserEmail(email);

            offerService.saveAddOffer(offer, owner);
            return "redirect:/offer/owner/list";
        }
    }

    @GetMapping("/owner/{id}/edit")
    public String edit(Model model, Authentication auth, @PathVariable(value = "id") Long id)
    {
        String email = auth.getName();
        User owner = userService.findByUserEmail(email);
        Offer offer = offerService.findByUserAndId(owner, id);
        model.addAttribute("offer", offer);

        return "views/offer/edit";
    }

    @PostMapping("/owner/edit")
    public String edit(@Valid Offer formOffer, BindingResult bresult)
    {
        if (bresult.hasErrors())
        {
            return "offer/edit";
        }
        else
        {

            offerService.saveEditOffer(formOffer);
            return "redirect:/offer/owner/list";
        }
    }

    @GetMapping("/owner/{id}/remove")
    public String remove(Authentication auth, @PathVariable(value = "id") Long offerId)
    {
        String email = auth.getName();
        User owner = userService.findByUserEmail(email);

        offerService.deleteOffer(offerId, owner);
        ;
        return "redirect:offer/owner/list";
    }

    @GetMapping("/owner/list")
    public String list(Model model, Authentication auth)
    {
        String email = auth.getName();
        User owner = userService.findByUserEmail(email);
        List<Offer> offers = new ArrayList<>();
        try
        {
            offers = offerService.findByUserId(owner.getId());
            model.addAttribute("offerList", offers);
        }
        catch (NullPointerException e)
        {
            offers = new ArrayList<>();
            model.addAttribute("offerList", offers);
        }

        return "views/offer/ownerOffersList";
    }

    @GetMapping("/owner/{id}/addPhoto")
    public String addPhoto(Model model, @PathVariable(value = "id") Long offerId, Authentication auth)
    {
        String email = auth.getName();
        User owner = userService.findByUserEmail(email);
        //  offer from path must be own by user who want add photo:
        Offer offer = offerService.findByUserAndId(owner, offerId);
        model.addAttribute("offer", offer);
        return "views/offer/addPhoto";
    }

    @PostMapping("/owner/addPhoto")
    public String addPhoto(@Valid Offer formOffer, BindingResult bresult, @RequestParam("photo") MultipartFile photo,
            RedirectAttributes redirectAttributes, Authentication auth)
    {
        if (bresult.hasErrors() || photo == null)
        {
            return "offer/owner/addPhoto";
        }
        else
        {
            String email = auth.getName();
            User owner = userService.findByUserEmail(email);

            storageService.store(photo);

            offerService.addPhoto(formOffer, owner, photo.getOriginalFilename());

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + photo.getOriginalFilename() + "!");

            return "redirect:/offer/ownerList";
        }
    }

    @GetMapping("/owner/{id}/details")
    public String details(Model model, @PathVariable(value = "id") Long offerId, Authentication auth)
    {
        String email = auth.getName();
        User owner = userService.findByUserEmail(email);
        //  only owner can see this page
        Offer offer = offerService.findByUserAndId(owner, offerId);
        
        model.addAttribute("offer", offer);
        
        
        return "views/offer/ownerOffersDetails";
    }

    //    @GetMapping("/{id}/details")
    //    public String list(Model model, @PathVariable(value = "id") Long id)
    //    {
    //        List<LocalDateTime> availableDates = availabilityService.findAvailableDatesByOffersId(id);
    //        Collections.sort(availableDates);                                                                               // sort by year
    //
    //        model.addAttribute("availableDates", availableDates);

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
    //
    //        return "views/offer/details";
    //    }
}
