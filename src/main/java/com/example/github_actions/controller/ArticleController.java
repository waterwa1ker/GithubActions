package com.example.github_actions.controller;

import com.example.github_actions.dto.ArticleDTO;
import com.example.github_actions.model.Article;
import com.example.github_actions.service.ArticleService;
import io.micrometer.core.annotation.Timed;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @Timed("findAllHttp")
    @GetMapping("/articles")
    public List<ArticleDTO> findAll() {
        return articleService.findAll()
                .stream().map(this::fromArticle)
                .collect(Collectors.toList());
    }


    private ArticleDTO fromArticle(Article article) {
        return modelMapper.map(article, ArticleDTO.class);
    }
}