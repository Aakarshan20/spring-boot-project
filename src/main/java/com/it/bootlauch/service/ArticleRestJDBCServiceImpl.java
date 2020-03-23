package com.it.bootlauch.service;

import com.it.bootlauch.dao.ArticleJDBCDAO;
import com.it.bootlauch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class ArticleRestJDBCServiceImpl implements ArticleRestService{

    @Resource
    ArticleJDBCDAO articleJDBCDAO;

    @Resource
    JdbcTemplate primaryJdbcTemplate;

    @Resource
    JdbcTemplate secondaryJdbcTemplate;

    @Transactional
    @Override
    public Article saveArticle(Article article){
        articleJDBCDAO.save(article, primaryJdbcTemplate);
        articleJDBCDAO.save(article, secondaryJdbcTemplate);

        int a = 1/0;

        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleJDBCDAO.deleteById(id, primaryJdbcTemplate);
    }

    @Override
    public void updateArticle(Article article) {
        articleJDBCDAO.updateById(article, primaryJdbcTemplate);
    }

    @Override
    public Article getArticle(Long id) {
        return articleJDBCDAO.findById(id, secondaryJdbcTemplate);
    }

    @Override
    public List<Article> getAll() {
        return articleJDBCDAO.findAll(primaryJdbcTemplate);
    }
}
