package com.it.bootlauch.service;

import com.it.bootlauch.jpa.testdb.ArticleJDBCDAO;
import com.it.bootlauch.model.ArticleVO;
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
    public ArticleVO saveArticle(ArticleVO articleVO){
        articleJDBCDAO.save(articleVO, primaryJdbcTemplate);

        //int a = 1/0;

        return articleVO;
    }

    @Override
    public void deleteArticle(Long id) {
        articleJDBCDAO.deleteById(id, primaryJdbcTemplate);
    }

    @Override
    public void updateArticle(ArticleVO articleVO) {
        articleJDBCDAO.updateById(articleVO, primaryJdbcTemplate);
    }

    @Override
    public ArticleVO getArticle(Long id) {
        return articleJDBCDAO.findById(id, primaryJdbcTemplate);
    }

    @Override
    public List<ArticleVO> getAll() {
        return articleJDBCDAO.findAll(primaryJdbcTemplate);
    }
}
