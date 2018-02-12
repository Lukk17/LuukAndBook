package pl.lukk.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pl.lukk.entity.User;
import pl.lukk.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping
    public String user(Authentication auth, Model model)
    {
        model.addAttribute("user", userService.findByUserEmail(auth.getName()));

        return "views/user";
    }
    
    @GetMapping("/add")
    public String add(Model model)
    {
        model.addAttribute("user", new User());

        return "views/user/add";
    }

    @PostMapping("/add")
    public String add(@Valid User user, BindingResult bresult)
    {
        if (bresult.hasErrors())
        {
            return "/views/user/add";
        }
        else
        {
            userService.saveUser(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/edit")
    public String edit(Model model, Authentication auth)
    {
        model.addAttribute("user", userService.findByUserEmail(auth.getName()));

        return "views/user/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid User formUser, BindingResult bresult, Authentication auth)
    {
        String email = auth.getName();
        User databaseUser = userService.findByUserEmail(email);

        if (bresult.hasErrors() || !userService.checkPassword(formUser.getPassword(), databaseUser.getPassword()))
        {
            return "user/edit";
        }
        else
        {
            userService.saveEditUser(databaseUser, formUser);

            return "redirect:/user";
        }
    }

    @GetMapping("/list")
    public String list(Model model, @SortDefault("id") Pageable pageable)
    {
        model.addAttribute("page", userService.findAll(pageable));
        return "views/user/list";
    }
    
    @GetMapping("/changePhoto")
    public String photoChange()
    {
        return "views/user/changePhoto";
    }
    
    @PostMapping("/changePhoto")
    public String photoChange(@RequestParam("photo") MultipartFile photo, 
             Authentication auth)
    {
        userService.savePhoto(auth.getName(),photo);
        
        return "redirect:/user";
    }

    @ModelAttribute
    public void addAttributes(Model model, Authentication auth)
    {
        User logedUser = userService.findByUserEmail(auth.getName());
        model.addAttribute("logedUser", logedUser );
    }
}
