package com.it.bootlauch.controller;

import com.it.bootlauch.model.Article;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        Article article = new Article(1L, "aaa");
        article.setAuthor("bbb");
        System.out.println(article.getAuthor());

        return "Hello World";
    }
}
