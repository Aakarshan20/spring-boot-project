package com.it.bootlauch.jpa.testdb;

import com.it.bootlauch.model.ArticleVO;
import com.it.bootlauch.response.BaseException;
import com.it.bootlauch.response.ResponseCode;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleJDBCDAO {
    @Resource
    private JdbcTemplate primaryJdbcTemplate ;

    public void save(ArticleVO articleVO, JdbcTemplate jdbcTemplate){
        jdbcTemplate.update("insert into article (author, title, content) values (?, ?, ?)",
                articleVO.getAuthor(),
                articleVO.getTitle(),
                articleVO.getContent());
    }

    public void deleteById(Long id, JdbcTemplate jdbcTemplate){
        jdbcTemplate.update("delete from article where id = ?", new Object[]{id});
    }

    public void updateById(ArticleVO articleVO, JdbcTemplate jdbcTemplate){
        jdbcTemplate.update("update article set author=?, title=?, content=? where id=?",
                articleVO.getTitle(),
                articleVO.getAuthor(),
                articleVO.getContent(),
                articleVO.getId());
    }

    public ArticleVO findById(Long id, JdbcTemplate jdbcTemplate){
        ArticleVO articleVO = null;
        try{
            articleVO =(ArticleVO) jdbcTemplate.queryForObject("select * from article where id = ?", new Object[]{id}, new BeanPropertyRowMapper(ArticleVO.class));
        } catch(Exception e){
            throw new BaseException(ResponseCode.RESOURCES_NOT_EXIST);
        }
        return articleVO;
    }

    public List<ArticleVO> findAll(JdbcTemplate jdbcTemplate){
        List<ArticleVO> articleVOS =  (List<ArticleVO>)jdbcTemplate.query("select * from article ", new BeanPropertyRowMapper(ArticleVO.class));
        if(articleVOS.size() == 0){
            throw new BaseException(ResponseCode.RESOURCES_NOT_EXIST);
        }
        return articleVOS;

    }
}
