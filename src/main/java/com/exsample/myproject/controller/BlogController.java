package com.exsample.myproject.controller;

import com.exsample.myproject.domain.TitleBlog;
import com.exsample.myproject.domain.User;
import com.exsample.myproject.repos.TitleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BlogController {


    @Autowired
    private TitleRepo titleRepo;

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/blog")
    public String blog(Map<String, Object> model) {
        Iterable<TitleBlog> titles = titleRepo.findAll();
        model.put("titles", titles);
        return "blog";
    }

    @PostMapping("/blog")
    public String addTitle(@AuthenticationPrincipal User user,
            @RequestParam String titleName,
            @RequestParam String titleTag, Map<String, Object> model) {
        TitleBlog title = new TitleBlog(titleName, titleTag, user);
        titleRepo.save(title);

        Iterable<TitleBlog> titles = titleRepo.findAll();
        model.put("titles", titles);
        return "blog";

    }

    @GetMapping("/filter")
    public String filter(Map<String, Object> model) {
        Iterable<TitleBlog> titles = titleRepo.findAll();
        model.put("titles", titles);
        return "filter";
    }


    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        Iterable<TitleBlog> titles;

        if (filter!=null && !filter.isEmpty()){
            titles = titleRepo.findByTitleTag(filter);
        }else {
            titles = titleRepo.findAll();
        }

        model.put("titles", titles);
        return ("filter");
    }

}
