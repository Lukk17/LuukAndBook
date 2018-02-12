package pl.lukk.controller;

import java.util.ArrayList;
import java.util.List;

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

import pl.lukk.entity.Role;
import pl.lukk.entity.User;
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

    @GetMapping("/panel")
    public String panel()
    {

        return "views/admin/panel";
    }

    @GetMapping("/userList")
    public String userList(Model model, @SortDefault("id") Pageable pageable)
    {

        model.addAttribute("userList", userService.findAll(pageable));

        return "views/admin/userList";
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
        User databaseUser = userService.findByUserId(formUser.getId());
        userService.saveEditUser(databaseUser, formUser);

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
    
    @ModelAttribute
    public void addAttributes(Model model, Authentication auth)
    {
        User logedUser = userService.findByUserEmail(auth.getName());
        model.addAttribute("logedUser", logedUser );
    }

}
