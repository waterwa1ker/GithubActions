package com.example.github_actions.repository;

import com.example.github_actions.model.Article;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Timed
    List<Article> findAll();
    Optional<Article> findByText(String text);

}
