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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stayeasyhalifax.R;
import com.example.stayeasyhalifax.adapters.GuestListAdapter;
import com.example.stayeasyhalifax.models.GuestData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuestDetailsListFragment extends Fragment{
    View view;
    TextView hotelNameTextView, checkInDateTextView, checkOutDateTextView, priceTextView, guestNumberTextView;
    Button bookConfirmButton;
    List<GuestData> guestData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.guest_details_list_layout, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hotelNameTextView = view.findViewById(R.id.hotel_name_recap_text_view);
        checkInDateTextView = view.findViewById(R.id.check_in_date_recap_text_view);
        checkOutDateTextView = view.findViewById(R.id.check_out_date_recap_text_view);
        priceTextView = view.findViewById(R.id.hotel_price_recap_text_view);
        guestNumberTextView = view.findViewById(R.id.guest_details_header_text_view);
        bookConfirmButton = view.findViewById(R.id.book_confirm_button);
        assert getArguments() != null;
        String hotelName = getArguments().getString("hotel name");
        String hotelPrice = getArguments().getString("hotel price");
        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");

        int guestSize = Integer.parseInt(numberOfGuests);
        guestData = new ArrayList<GuestData>();
        for (int i = 0; i < guestSize; i++) {
            guestData.add(new GuestData("", ""));
        }

        hotelNameTextView.setText(String.format("Booking your stay at %s from", hotelName));
        checkInDateTextView.setText(checkInDate);
        checkOutDateTextView.setText(checkOutDate);
        priceTextView.setText(String.format("for %s", hotelPrice));
        guestNumberTextView.setText(String.format("Enter the %s guests details below", numberOfGuests));

        RecyclerView recyclerView = view.findViewById(R.id.guest_details_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GuestListAdapter guestListAdapter = new GuestListAdapter(getActivity(), guestData);
        recyclerView.setAdapter(guestListAdapter);


        bookConfirmButton.setOnClickListener(view1 -> {

            UUID bookingID = UUID.randomUUID();
            Bundle bundle = new Bundle();
            bundle.putString("booking id", bookingID.toString());

            BookingConfirmedFragment bookingConfirmedFragment = new BookingConfirmedFragment();
            bookingConfirmedFragment.setArguments(bundle);
            assert getFragmentManager() != null;
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_layout, bookingConfirmedFragment);
            fragmentTransaction.remove(GuestDetailsListFragment.this);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }
}
