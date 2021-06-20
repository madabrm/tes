package com.example.quotes.models.retrofit;

import com.example.quotes.models.Quote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("random")
    Call<Quote> getQuote();
}
