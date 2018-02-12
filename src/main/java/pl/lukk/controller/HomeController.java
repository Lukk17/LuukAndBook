package pl.lukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lukk.entity.User;
import pl.lukk.service.StorageService;
import pl.lukk.service.UserService;

@Controller
public class HomeController
{
    @Autowired
    UserService userService;

    @Autowired
    StorageService storageService;

    @GetMapping("/403")
    public String permissionError()
    {
        return "404";
    }

    @RequestMapping(value =
    { "/login" }, method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }

    @GetMapping("/admin")
    public String admin()
    {
        return "views/admin";
    }

    

    @GetMapping(
    { "/", "/index" })
    public String home()
    {
        return "index";
    }

    @GetMapping("/layout")
    public String layout()
    {
        return "layout";
    }

    @GetMapping("/form")
    public String form()
    {
        return "form-template";
    }

    @ModelAttribute
    public void addAttributes(Model model, Authentication auth)
    {
        User logedUser = userService.findByUserEmail(auth.getName());
        model.addAttribute("logedUser", logedUser );
    }

}
