package pl.lukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pl.lukk.entity.User;
import pl.lukk.service.BookedDateService;
import pl.lukk.service.MessageService;
import pl.lukk.service.OfferService;
import pl.lukk.service.UserService;

@Controller
@RequestMapping("/booking")
public class BookedDateController
{
    @Autowired
    BookedDateService bookedService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    MessageService messageService;
    
    @Autowired
    OfferService offerService;
    
    @GetMapping("/{id}/dates")
    public String datesList(@PathVariable(value = "id") Long offerId , Model model, @RequestParam(value="page", required=false) Integer page)
    {   Integer pages = bookedService.findAvailableDatesByOffersId(offerId, page).getTotalPages();
        List<Integer> listPages = new ArrayList<>();
        for (int i = 0; i < pages-1; i++)
        {
            listPages.add(i);
        }
        model.addAttribute("pages", listPages);
        model.addAttribute("id", offerId);
        model.addAttribute("availableDates", bookedService.findAvailableDatesByOffersId(offerId, page));
        return "views/offer/dates";
    }
    
    @PostMapping("/offer/book")
    public String datesList(@RequestParam(value="date", required=false) List<String> dateToBookList, @RequestParam("id") Long offerId, Authentication auth)
    {
        bookedService.bookDates(dateToBookList, offerId, auth.getName());
        return "redirect:/offer/user/list";
    }
    
    @GetMapping("/owner/{id}/booked")
    public String bookedDates(@PathVariable(value = "id") Long offerId, Model model)
    {
        model.addAttribute("dates", bookedService.findAllBookedDatesByOffer(offerId));
        ;
        return "views/offer/bookedDates";
    }
    
    @ModelAttribute
    public void addAttributes(Model model, Authentication auth)
    {
        try
        {
            User logged = userService.findByUserEmail(auth.getName());
            model.addAttribute("logedUser", logged);
            model.addAttribute("topMessages", messageService.findTop5ByOrderByCreated(logged));
            model.addAttribute("msgNum", messageService.unreadedNum(auth.getName()));
            model.addAttribute("topUserOffer", offerService.findTop5ByUser(auth.getName()));
            model.addAttribute("userOfferNum", offerService.userOfferNum(auth.getName()));
            model.addAttribute("topOwnerOffer", offerService.findTop5ByOwner(auth.getName()));
            model.addAttribute("ownerOfferNum", offerService.ownerOfferNum(auth.getName()));
        }
        catch (NullPointerException e)
        {

        }
    }
    
}
