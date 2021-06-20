package com.example.quotes.models.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(QuoteEntity quote);

    @Query("DELETE FROM quotes_table")
    void deleteAll();

    @Query("SELECT * from quotes_table ORDER BY id ASC")
    LiveData<List<QuoteEntity>> getAllQuotes();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateQuote(QuoteEntity quote);

    @Delete
    void deleteQuote(QuoteEntity quote);
}
