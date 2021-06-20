package com.example.quotes.models.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {QuoteEntity.class}, version = 1)
public abstract class QuoteRoomDatabase extends RoomDatabase {
    public abstract QuoteDao dao();

    private static QuoteRoomDatabase INSTANCE;

    public static QuoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuoteRoomDatabase.class, "quotes_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
