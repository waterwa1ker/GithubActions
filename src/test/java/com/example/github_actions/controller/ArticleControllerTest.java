package com.example.github_actions.controller;

import com.example.github_actions.dto.ArticleDTO;
import com.example.github_actions.model.Article;
import com.example.github_actions.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

    @Mock
    private ArticleService articleService;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ArticleController articleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    void findAll() throws Exception {
        int size = 5;
        List<Article> articles = new ArrayList<>();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            articles.add(new Article(String.format("Text: %d", i), String.format("Title: %d", i)));
            articleDTOS.add(new ArticleDTO(String.format("Text: %d", i), String.format("Title: %d", i)));
            Mockito.when(modelMapper.map(articles.get(i), ArticleDTO.class)).thenReturn(articleDTOS.get(i));
        }
        Mockito.when(articleService.findAll()).thenReturn(articles);
        ResultActions expect = mockMvc
                .perform(get("/api/v1/articles"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(size)));
        for (int i = 0; i < 5; ++i) {
            expect
                    .andExpect(jsonPath(String.format("$[%d].text", i), is(articleDTOS.get(i).getText())))
                    .andExpect(jsonPath(String.format("$[%d].title", i), is(articleDTOS.get(i).getTitle())));
        }

    }


}
