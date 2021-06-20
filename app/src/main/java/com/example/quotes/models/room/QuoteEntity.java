package com.example.quotes.models.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "quotes_table")
public class QuoteEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "quote_text")
    private String quoteText;

    @NonNull
    @ColumnInfo(name = "quote_author")
    private String quoteAuthor;

    @ColumnInfo(name = "quote_state")
    private boolean isLiked;

    public QuoteEntity(@NonNull String quoteText, @NonNull String quoteAuthor) {
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
        this.isLiked = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(@NonNull String quoteText) {
        this.quoteText = quoteText;
    }

    @NonNull
    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(@NonNull String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
