package com.example.stayeasyhalifax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.stayeasyhalifax.R;

public class BookingConfirmedFragment extends Fragment {
    View view;
    TextView bookingConfirmedTextView;
    Button backToSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.booking_confirmed_layout, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        String bookingID = getArguments().getString("booking id");
        bookingConfirmedTextView = view.findViewById(R.id.booking_confirmed_text_view);
        bookingConfirmedTextView.setText(String.format(getString(R.string.booking_confirmed_message), bookingID));
        backToSearch = view.findViewById(R.id.backtoSearch);
        backToSearch.setOnClickListener(v -> {
            SearchHomeFragment searchHomeFragment = new SearchHomeFragment();
            assert getFragmentManager() != null;
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_layout, searchHomeFragment);
            fragmentTransaction.remove(BookingConfirmedFragment.this);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }
}
