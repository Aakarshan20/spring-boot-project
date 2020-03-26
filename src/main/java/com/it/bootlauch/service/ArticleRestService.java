package com.it.bootlauch.service;


import com.it.bootlauch.model.ArticleVO;

import java.util.List;

public interface ArticleRestService {
    ArticleVO saveArticle(ArticleVO articleVO);

    void deleteArticle(Long id);

    void updateArticle(ArticleVO articleVO);

    ArticleVO getArticle(Long id);

    List<ArticleVO> getAll();
}
