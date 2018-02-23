package pl.lukk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;
import pl.lukk.service.MessageService;
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

    @Autowired
    MessageService messageService;

    @GetMapping("/owner/add")
    public String add(Model model)
    {
        model.addAttribute("offer", new Offer());

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
            offerService.saveAddOffer(offer, auth.getName());
            return "redirect:/offer/owner/list";
        }
    }

    @GetMapping("/owner/{id}/edit")
    public String edit(Model model, Authentication auth, @PathVariable(value = "id") Long id)
    {
        model.addAttribute("offer", offerService.findByUserAndId(auth.getName(), id));

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
        offerService.deleteOffer(offerId, auth.getName());

        return "redirect:/offer/owner/list";
    }

    @GetMapping("/owner/list")
    public String ownerList(Model model, Authentication auth)
    {
        List<Offer> offers = offerService.findByUserId(auth.getName());
        model.addAttribute("offerList", offers);

        return "views/offer/ownerOffersList";
    }

    @GetMapping("/owner/{id}/addPhoto")
    public String addPhoto(Model model, @PathVariable(value = "id") Long offerId, HttpSession ses)
    {
        ses.setAttribute("offerId", offerId);
        return "views/offer/addPhoto";
    }

    @PostMapping("/owner/addPhoto")
    public String addPhoto(@RequestParam("photo") MultipartFile photo, RedirectAttributes redirectAttributes,
            Authentication auth, HttpSession ses)
    {
        Long offerId = (Long) ses.getAttribute("offerId");

        offerService.addPhoto(offerId, auth.getName(), photo.getOriginalFilename());
        storageService.store(photo);

        ses.removeAttribute("offerId");

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + photo.getOriginalFilename() + "!");

        return "redirect:/offer/owner/list";

    }

    @GetMapping("/owner/{id}/details")
    public String ownerDetails(Model model, @PathVariable(value = "id") Long offerId, Authentication auth)
    {
        //  only owner can see this page
        Offer offer = offerService.findByUserAndId(auth.getName(), offerId);
        model.addAttribute("offer", offer);

        return "views/offer/ownerOffersDetails";
    }

    @GetMapping("/user/list")
    public String userList(Model model, Authentication auth)
    {
        List<Offer> offers = offerService.findByBookedDatesUser(auth.getName());
        model.addAttribute("offerList", offers);

        return "views/offer/userOffersList";
    }
    
    @GetMapping("/user/{id}/details")
    public String userDetails(Model model, @PathVariable(value = "id") Long offerId)
    {
        Offer offer = offerService.findOneById(offerId);
        model.addAttribute("offer", offer);

        return "views/offer/userOffersDetails";
    }
    
    @GetMapping("/user/search")
    public String search(Model model)
    {
        model.addAttribute("offer", new Offer());

        return "views/offer/search";
    }
    
    @PostMapping("/user/search")
    public String search(Offer offer, Model model)
    {
        model.addAttribute("offerList", offerService.search(offer));
        return "views/offer/userOffersList";
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
