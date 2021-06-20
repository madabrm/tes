package com.example.quotes.models;


public class Quote {

    private String content;
    private String author;

    public Quote() {}

    public Quote(String text, String author) {
        this.content = text;
        this.author = author;
    }

    public String getQuoteText() {
        return content;
    }

    public String getQuoteAuthor() {
        return author;
    }

}
