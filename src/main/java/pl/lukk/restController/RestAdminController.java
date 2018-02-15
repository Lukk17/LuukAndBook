package pl.lukk.restController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;
import pl.lukk.service.MessageService;
import pl.lukk.service.OfferService;
import pl.lukk.service.RoleService;
import pl.lukk.service.UserService;

@RestController
@RequestMapping("/restAdmin")
public class RestAdminController
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
    public ResponseEntity userList(Model model, @SortDefault("id") Pageable pageable)
    {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/offerList")
    public ResponseEntity offerList(Model model, @SortDefault("id") Pageable pageable)
    {
        return ResponseEntity.ok(offerService.findALL(pageable));
    }

    @GetMapping("/{id}/userEdit")
    public ResponseEntity edit(Model model, @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(userService.findByUserId(id));
    }

    @PutMapping("/userEdit")
    public ResponseEntity edit(User formUser)
    {
        userService.adminEditUser(formUser);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}/roleChange")
    public ResponseEntity roleEdit(Model model, @PathVariable(name = "id") Long id)
    {

        return ResponseEntity.ok(userService.findByUserId(id));
    }

    @PutMapping("/roleChange")
    public ResponseEntity roleEdit(@RequestParam(value = "ROLE_USER", required = false) Long user,
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

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}/userRemove")
    public ResponseEntity remove(Model model, @PathVariable(name = "id") Long id)
    {
        userService.delete(id);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/offer/{id}/edit")
    public ResponseEntity offerEdit(Model model, @PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok(offerService.adminFindOfferById(id));
    }

    @PutMapping("/offer/edit")
    public ResponseEntity offerEdit(@Valid Offer formOffer, BindingResult bresult)
    {
        if (bresult.hasErrors())
        {
            return ResponseEntity.badRequest().body(formOffer);
        }
        else
        {
            offerService.saveEditOffer(formOffer);
            return ResponseEntity.accepted().build();
        }
    }
}
