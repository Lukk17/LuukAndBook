package pl.lukk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lukk.entity.User;
import pl.lukk.service.MessageService;
import pl.lukk.service.OfferService;
import pl.lukk.service.StorageService;
import pl.lukk.service.UserService;

@Controller
public class HomeController
{
    public static String logedSubscriverEmail;

    @Autowired
    UserService userService;

    @Autowired
    StorageService storageService;

    @Autowired
    MessageService messageService;

    @Autowired
    OfferService offerService;

    @GetMapping("/403")
    public String permissionError()
    {
        return "403";
    }

    @GetMapping("/500")
    public String serverError()
    {
        return "500";
    }

    @RequestMapping(value =
    { "/login" }, method = RequestMethod.GET)
    public String login()
    {

        return "login";
    }

    @GetMapping(
    { "/", "/index" })
    public String home(Model model, @SortDefault("hotelName") Pageable pageable, Authentication auth)
    {
        if (auth!=null)
        {
            logedSubscriverEmail = auth.getName();
        }
        model.addAttribute("offerList", offerService.findALL(pageable));

        return "index";
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
