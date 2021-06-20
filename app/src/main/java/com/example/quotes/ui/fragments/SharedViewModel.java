package com.example.quotes.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.view.View;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<View> selected = new MutableLiveData<>();

    public void select(View view) {
        selected.setValue(view);
    }

    public LiveData<View> getSelected() {
        return selected;
    }
}