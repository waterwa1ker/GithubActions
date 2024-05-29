package com.example.github_actions.service;

import com.example.github_actions.model.Article;
import com.example.github_actions.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;
    @Mock
    private ArticleRepository articleRepository;

    @Test
    void findAll() {
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            articles.add(new Article(String.format("Title: %d", i), String.format("Text: %d", i)));
        }
        Mockito.when(articleRepository.findAll()).thenReturn(articles);
        Assertions.assertIterableEquals(articles, articleService.findAll());
    }

    @Test
    void findById() {
        int id = 1;
        Article article = new Article("title", "text");
        article.setId(id);
        Optional<Article> optionalArticle = Optional.of(article);
        Mockito.when(articleRepository.findById(id)).thenReturn(optionalArticle);
        Assertions.assertEquals(articleService.findById(id), optionalArticle);
    }

}
