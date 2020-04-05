package com.it.bootlauch.jpa.testdb;

import com.it.bootlauch.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findByAuthorLike(String author);
}
