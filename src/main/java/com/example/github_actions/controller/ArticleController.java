package com.example.github_actions.controller;

import com.example.github_actions.dto.ArticleDTO;
import com.example.github_actions.model.Article;
import com.example.github_actions.service.ArticleService;
import io.micrometer.core.instrument.MeterRegistry;
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
    private final MeterRegistry meterRegistry;

    @Autowired
    public ArticleController(ArticleService articleService, ModelMapper modelMapper, MeterRegistry meterRegistry) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/articles")
    public List<ArticleDTO> findAll() {
        meterRegistry
                .counter("find-all-counter", List.of())
                .increment();
        return articleService.findAll()
                .stream().map(this::fromArticle)
                .collect(Collectors.toList());
    }


    private ArticleDTO fromArticle(Article article) {
        return modelMapper.map(article, ArticleDTO.class);
    }
}