package com.it.bootlauch.service;

import com.it.bootlauch.jpa.testdb.ArticleRepository;
import com.it.bootlauch.jpa.testdb.Article;
import com.it.bootlauch.jpa.testdb2.Message;
import com.it.bootlauch.jpa.testdb2.MessageRepository;
import com.it.bootlauch.model.ArticleVO;
import com.it.bootlauch.utils.DozerUtils;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleJPARestService implements ArticleRestService{
    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private MessageRepository messageRepository;

    @Resource
    private Mapper dozerMapper;

    @Override
    public ArticleVO saveArticle(ArticleVO articleVO) {
        Article articlePO = dozerMapper.map(articleVO, Article.class);
        articleRepository.save(articlePO);

        Message message = new Message();
        message.setName("kobe");
        message.setContent("retired");
        messageRepository.save(message);

        Optional<Message> message2 = messageRepository.findById(1L);
        System.out.println(message2);

        return articleVO;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void updateArticle(ArticleVO articleVO) {
        Article articlePO = dozerMapper.map(articleVO, Article.class);
        articleRepository.save(articlePO);
    }

    @Override
    public ArticleVO getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        ArticleVO articleVO =dozerMapper.map(article.get(), ArticleVO.class);
        //articleVO.setReader();
        return articleVO;
    }

    @Override
    public List<ArticleVO> getAll() {
        List<Article> articles = articleRepository.findAll();
        return DozerUtils.mapList(articles, ArticleVO.class);
    }
}
