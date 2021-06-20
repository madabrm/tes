package com.example.quotes.ui.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quotes.R;
import com.example.quotes.models.room.QuoteEntity;
import com.example.quotes.models.room.ViewModel;
import com.example.quotes.ui.adapters.MyQuotesAdapter;

import java.util.List;

public class MyQuotesFragment extends Fragment {

    private ViewModel viewModel;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_my_quotes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_my_quotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyQuotesAdapter adapter = new MyQuotesAdapter(getContext(), viewModel);
        recyclerView.setAdapter(adapter);

        viewModel.getAllQuotes().observe(this, new Observer<List<QuoteEntity>>() {
            @Override
            public void onChanged(@Nullable List<QuoteEntity> quoteEntities) {
                adapter.setmQuotes(quoteEntities);
            }
        });
        return view;
    }

}
