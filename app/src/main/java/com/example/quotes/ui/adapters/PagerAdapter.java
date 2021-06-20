package com.example.quotes.ui.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.annotation.NonNull;
import com.example.quotes.ui.fragments.DailyQuotesFragment;
import com.example.quotes.ui.fragments.MyQuotesFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DailyQuotesFragment dailyQuotes = new DailyQuotesFragment();
                return dailyQuotes;
            case 1:
                MyQuotesFragment myQuotes = new MyQuotesFragment();
                return myQuotes;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
