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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lukk.entity.Message;
import pl.lukk.entity.User;
import pl.lukk.service.MessageService;
import pl.lukk.service.OfferService;
import pl.lukk.service.UserService;

@Controller
@RequestMapping("/message")
public class MessageController
{
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;
    
    @Autowired
    OfferService offerService;

    @GetMapping("/inbox")
    public String inbox(Model model, @SortDefault("created") Pageable pageable, Authentication auth)
    {

        model.addAttribute("inbox", messageService.findAllByWhereReceiverEmail(auth.getName(), pageable));

        return "views/message/inbox";
    }

    @GetMapping("/outbox")
    public String outbox(Model model, @SortDefault("created") Pageable pageable, Authentication auth)
    {

        model.addAttribute("outbox", messageService.findAllByWhereSenderEmail(auth.getName(), pageable));

        return "views/message/outbox";
    }

    @GetMapping("/{id}/send")
    public String send(Model model, Authentication auth, @PathVariable(value = "id") Long receiverId, HttpSession ses)
    {
        Message msg = new Message();
        model.addAttribute("msg", msg);
        ses.setAttribute("receiverId", receiverId);

        return "views/message/send";
    }

    @PostMapping("/send")
    public String send(Message message, Authentication auth, HttpSession ses)
    {
        messageService.send(message, auth.getName(), (Long) ses.getAttribute("receiverId"));
        ses.removeAttribute("receiverId");

        return "redirect:/message/outbox";
    }

    @GetMapping("/{id}/messageRemove")
    public String remove(Authentication auth, @PathVariable(value = "id") Long messageId)
    {
        messageService.remove(messageId, auth.getName());

        return "redirect:/message/inbox";
    }

    @GetMapping("/{id}/receiverDetails")
    public String receiverDetails(@PathVariable(value = "id") Long messageId, Authentication auth, Model model)
    {
        messageService.readed(messageId, auth.getName());
        model.addAttribute("msg", messageService.findOneById(auth.getName(), messageId));

        return "views/message/receiverDetails";
    }

    @GetMapping("/{id}/senderDetails")
    public String senderDetails(@PathVariable(value = "id") Long messageId, Authentication auth, Model model)
    {
        model.addAttribute("msg", messageService.findOneById(auth.getName(), messageId));

        return "views/message/senderDetails";
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
