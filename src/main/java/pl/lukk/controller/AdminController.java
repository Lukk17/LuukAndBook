package pl.lukk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lukk.entity.User;
import pl.lukk.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    UserService userService;

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
        model.addAttribute("user", userService.findByUserId(id));

        return "views/admin/userEdit";
    }

    @PostMapping("/edit")
    public String edit(@Valid User formUser, BindingResult bresult, @PathVariable(name = "id") Long id)
    {

        User databaseUser = userService.findByUserId(id);

        if (bresult.hasErrors())
        {
            return "views/admin/userList";
        }
        else
        {
            if (formUser.getEmail() != null)
            {
                databaseUser.setEmail(formUser.getEmail());
            }
            if (formUser.getName() != null)
            {
                databaseUser.setName(formUser.getName());
            }
            if (formUser.getSurname() != null)
            {
                databaseUser.setSurname(formUser.getSurname());
            }

            userService.saveEditUser(databaseUser);

            return "redirect:index";
        }
    }
}
