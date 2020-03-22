package com.it.bootlauch.dao;

import com.it.bootlauch.model.Article;
import com.it.bootlauch.response.BaseException;
import com.it.bootlauch.response.ResponseCode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleJDBCDAO {
    @Resource
    private JdbcTemplate primaryJdbcTemplate ;

    public void save(Article article, JdbcTemplate jdbcTemplate){
        jdbcTemplate.update("insert into article (author, title, content) values (?, ?, ?)",
                article.getAuthor(),
                article.getTitle(),
                article.getContent());
    }

    public void deleteById(Long id, JdbcTemplate jdbcTemplate){
        jdbcTemplate.update("delete from article where id = ?", new Object[]{id});
    }

    public void updateById(Article article, JdbcTemplate jdbcTemplate){
        jdbcTemplate.update("update article set author=?, title=?, content=? where id=?",
                article.getTitle(),
                article.getAuthor(),
                article.getContent(),
                article.getId());
    }

    public Article findById(Long id, JdbcTemplate jdbcTemplate){
        Article article = null;
        try{
            article =(Article) jdbcTemplate.queryForObject("select * from article where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Article.class));
        } catch(Exception e){
            throw new BaseException(ResponseCode.RESOURCES_NOT_EXIST);
        }
        return article;
    }

    public List<Article> findAll(JdbcTemplate jdbcTemplate){
        List<Article> articles =  (List<Article>)jdbcTemplate.query("select * from article ", new BeanPropertyRowMapper(Article.class));
        if(articles.size() == 0){
            throw new BaseException(ResponseCode.RESOURCES_NOT_EXIST);
        }
        return articles;

    }
}
