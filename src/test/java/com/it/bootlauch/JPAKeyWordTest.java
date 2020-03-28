package com.it.bootlauch;

import com.it.bootlauch.dao.ArticleRepository;
import com.it.bootlauch.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPAKeyWordTest {
    @Resource
    private ArticleRepository articleRepository;

    @Test
    public void userTest() {
        Article article = articleRepository.findByAuthorLike("%多數據源測試%");
        System.out.println(article);
    }
}