package com.it.bootlauch.controller;

import com.it.bootlauch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public Article hello(){
//        Article article = new Article(1L, "aaa");
//        article.setAuthor("bbb");
//        System.out.println(article.getAuthor());

        Article article1 = Article.builder().id(22L).author("ccc").build();
        log.info("測試一下" + article1.getId());
        //return "Hello World";
        return article1;
    }
}
