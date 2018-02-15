package pl.lukk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
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

import pl.lukk.entity.Offer;
import pl.lukk.entity.Role;
import pl.lukk.entity.User;
import pl.lukk.service.MessageService;
import pl.lukk.service.OfferService;
import pl.lukk.service.RoleService;
import pl.lukk.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    MessageService messageService;
    
    @Autowired
    OfferService offerService;

    @GetMapping("/userList")
    public String userList(Model model, @SortDefault("id") Pageable pageable)
    {

        model.addAttribute("userList", userService.findAll(pageable));

        return "views/admin/userList";
    }
    
    @GetMapping("/offerList")
    public String offerList(Model model, @SortDefault("id") Pageable pageable)
    {

        model.addAttribute("offerList", offerService.findALL(pageable));

        return "views/admin/offerList";
    }

    @GetMapping("/{id}/userEdit")
    public String edit(Model model, @PathVariable(name = "id") Long id)
    {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("user", userService.findByUserId(id));

        return "views/admin/userEdit";
    }

    @PostMapping("/userEdit")
    public String edit(User formUser)
    {
        userService.adminEditUser(formUser);

        return "redirect:/admin/userList";

    }

    @GetMapping("/{id}/roleChange")
    public String roleEdit(Model model, @PathVariable(name = "id") Long id)
    {
        model.addAttribute("user", userService.findByUserId(id));

        return "views/admin/roleChange";
    }

    @PostMapping("/roleChange")
    public String roleEdit(@RequestParam(value = "ROLE_USER", required = false) Long user,
            @RequestParam(value = "ROLE_OWNER", required = false) Long owner,
            @RequestParam(value = "ROLE_ADMIN", required = false) Long admin,
            @RequestParam(value = "ROLE_GROUP-MANAGER", required = false) Long groupManager,
            @RequestParam(value = "id", required = false) Long userId)
    {
        List<Long> rolesId = new ArrayList<>();
        rolesId.add(user);
        rolesId.add(owner);
        rolesId.add(admin);
        rolesId.add(groupManager);

        userService.roleChange(userId, rolesId);

        return "redirect:/admin/userList";
    }

    @GetMapping("/{id}/userRemove")
    public String remove(Model model, @PathVariable(name = "id") Long id)
    {
        userService.delete(id);

        return "views/admin/roleChange";
    }
    
    @GetMapping("/offer/{id}/edit")
    public String offerEdit(Model model, @PathVariable(value = "id") Long id)
    {
        model.addAttribute("offer", offerService.adminFindOfferById(id));

        return "views/admin/offerEdit";
    }

    @PostMapping("/offer/edit")
    public String offerEdit(@Valid Offer formOffer, BindingResult bresult)
    {
        if (bresult.hasErrors())
        {
            return "admin/offer/edit";
        }
        else
        {

            offerService.saveEditOffer(formOffer);
            return "redirect:/admin/offerList";
        }
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
