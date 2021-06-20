package com.example.quotes.models.retrofit;

import com.example.quotes.models.Quote;

public interface QuoterDataRepository {

    void getQuote(QuoteListener cityListener);
    interface QuoteListener {

        void onQuoteReady(Quote quoteOfTheDay);

        void onQuoteFail();

    }
}
