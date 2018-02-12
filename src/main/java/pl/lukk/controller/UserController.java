package pl.lukk.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.lukk.entity.User;
import pl.lukk.repository.UserRepository;
import pl.lukk.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String add(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);

        return "views/user/add";
    }

    @PostMapping("/add")
    public String add(User user)
    {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/edit")
    public String edit(Model model, Authentication auth)
    {
        String name = auth.getName();
        User user = userService.findByUserEmail(name);
        model.addAttribute("user", user);

        return "views/user/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid User formUser, BindingResult bresult, Authentication auth)
    {
        String name = auth.getName();
        User databaseUser = userService.findByUserEmail(name);

        if (bresult.hasErrors() || !userService.checkPassword(formUser.getPassword(), databaseUser.getPassword()))
        {
            return "views/user/edit";
        }
        else
        {
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

    @GetMapping("/list")
    public String list(Model model, @SortDefault("id") Pageable pageable)
    {
        model.addAttribute("page", userService.findAll(pageable));
        return "views/user/list";
    }

    @GetMapping("/add30")
    @ResponseBody
    public String add30()
    {
        for (int i = 0; i < 30; i++)
        {
            User user = new User();
            user.setEmail("name" + i + "@mail.com");
            user.setName("name" + 1);
            user.setSurname("surname" + i);
            userService.saveUser(user);
        }
        return "added30";
    }
}
