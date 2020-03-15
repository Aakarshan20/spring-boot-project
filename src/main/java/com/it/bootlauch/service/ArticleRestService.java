package com.it.bootlauch.service;

import com.it.bootlauch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ArticleRestService {
    public String saveArticle(Article article){
        log.info("save article:{}", article);
        return "test";
    }
}
