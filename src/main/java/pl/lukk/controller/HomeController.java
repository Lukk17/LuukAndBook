package pl.lukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.lukk.entity.User;
import pl.lukk.service.UserService;

@Controller
public class HomeController
{
    @Autowired
    UserService userService;

//    @GetMapping("/add-user")
//    @ResponseBody
//    public String addUser()
//    {
//        User u = new User();
//        u.setEmail("admin@admin");
//        u.setPassword("admin");
//        userService.saveUser(u);
//
//        return "add-user";
//    }

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

    @GetMapping("/user")
    public String user()
    {
        return "views/user";
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

}
