package com.example.applearning.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public String category;
    String word;
    String translate;

    public Words() {
    }
    public Words(Long id, String category, String word, String translate) {
        this.id = id;
        this.category = category;
        this.word = word;
        this.translate = translate;
    }

    public Words(String category, String word, String translate) {
        this.category = category;
        this.word = word;
        this.translate = translate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", word='" + word + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}
