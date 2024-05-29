package com.example.github_actions.dto;

public class ArticleDTO {

    private String title;

    private String text;

    public ArticleDTO() {
    }

    public ArticleDTO(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
