package pl.lukk.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.lukk.entity.Template;

@Controller
public class TemplateController
{
    @GetMapping("/template")
    public String template(Model model)
    {
        Template modelTemp = new Template();
        model.addAttribute("template", modelTemp);

        Template tmp = new Template();
        tmp.setName("tmpName");
        tmp.setValue(30);
        tmp.setDate(new Date(1992, 06, 17));
        model.addAttribute("tmp", tmp);
        model.addAttribute("message", "Hello World");

        Template tmp2 = new Template();
        tmp.setName("tmpName2");
        tmp.setValue(50);
        tmp.setDate(new Date(1992, 06, 17));

        Template tmp3 = new Template();
        tmp.setName("tmpName3");
        tmp.setValue(10);
        tmp.setDate(new Date(1992, 06, 17));

        Template tmp4 = new Template();
        tmp.setName("tmpName4");
        tmp.setValue(4);
        tmp.setDate(new Date(1992, 06, 17));

        List<Template> tmpList = new ArrayList<>();
        tmpList.add(tmp);
        tmpList.add(tmp2);
        tmpList.add(tmp3);
        tmpList.add(tmp4);
        model.addAttribute("tmpList", tmpList);

        return "template";
    }

    @PostMapping("/template")
    public String template()
    {
        return "template";
    }
}
