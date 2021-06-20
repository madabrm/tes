package com.example.quotes.ui.fragments;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quotes.R;
import com.example.quotes.models.Quote;
import com.example.quotes.models.retrofit.QuoterDataRepository;
import com.example.quotes.models.retrofit.RetrofitWrapper;
import com.example.quotes.models.room.QuoteEntity;
import com.example.quotes.models.room.ViewModel;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyQuotesFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.txt_author_quote_daily)
    TextView txtAuthor;
    @BindView(R.id.txt_quote_daily)
    TextView txtQuote;
    @BindView(R.id.img_btn_like_daily)
    ImageButton imgBtnLike;
    @BindView(R.id.img_btn_share_daily)
    ImageButton imgBtnShare;

    @BindView(R.id.layout)
    LinearLayout linearlayout;

    private SharedViewModel sharedViewModel;
    private ViewModel viewModel;
    private RetrofitWrapper wrapper;
    private Quote quoteOfTheDay;
    boolean clicked = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_quotes, container, false);
        ButterKnife.bind(this, view);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        imgBtnLike.setOnClickListener(v -> sharedViewModel.select(v));
        wrapper = RetrofitWrapper.getInstance();
        getQuote();
        imgBtnLike.setOnClickListener(this::onClick);
        imgBtnShare.setOnClickListener(this::onClick);
        setHasOptionsMenu(true);
        return view;
    }

    private void getQuote() {
        wrapper.getQuote(new QuoterDataRepository.QuoteListener() {
            @Override
            public void onQuoteReady(Quote quoteOfTheDay) {
                txtAuthor.setText(quoteOfTheDay.getQuoteAuthor());
                txtQuote.setText(quoteOfTheDay.getQuoteText());
                imgBtnLike.setImageResource(R.drawable.ic_favorite_border_black_24px);
                clicked = false;
                linearlayout.setBackgroundColor(getRandomColor());
            }

            @Override
            public void onQuoteFail() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == imgBtnLike) {
            if (clicked) {
                viewModel.delete(new QuoteEntity(txtQuote.getText().toString(), txtAuthor.getText().toString()));
                imgBtnLike.setImageResource(R.drawable.ic_favorite_border_black_24px);
                clicked = false;
            } else {
                viewModel.insert(new QuoteEntity(txtQuote.getText().toString(), txtAuthor.getText().toString()));
                imgBtnLike.setImageResource(R.drawable.ic_favorite_black_24px);
                clicked = true;
            }
        } else if (v == imgBtnShare) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = txtQuote.getText().toString();
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(intent.createChooser(intent, "Share using... "));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.quotes_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                update();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void update() {
             getQuote();
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
