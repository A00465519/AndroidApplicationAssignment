package com.example.stayeasyhalifax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stayeasyhalifax.R;
import com.example.stayeasyhalifax.adapters.HotelListAdapter;
import com.example.stayeasyhalifax.models.HotelData;
import com.example.stayeasyhalifax.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchResultListFragment extends Fragment implements ItemClickListener {

    View view;
    TextView displayInfoTextView;
    ProgressBar progressBar;
    List<HotelData> userListResponseData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.search_result_list_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayInfoTextView = view.findViewById(R.id.display_info_text);
        progressBar = view.findViewById(R.id.progress_bar);

        assert getArguments() != null;
        String name = getArguments().getString("Name");
        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");
//      Build the info text
        displayInfoTextView.setText(String.format(getString(R.string.display_info), name, numberOfGuests, checkInDate, checkOutDate));

        ArrayList<HotelData> hotelList = initHotelList();

        RecyclerView recyclerView = view.findViewById(R.id.search_results_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelList);
        recyclerView.setAdapter(hotelListAdapter);
    }

    public ArrayList<HotelData> initHotelList() {
        ArrayList<HotelData> list = new ArrayList<>();

        list.add(new HotelData("Halifax Regional Hotel", "2000$", "North Street", "true", 4.5));
        list.add(new HotelData("Hotel Pearl",  "2000$", "North Street", "true", 4.6));
        list.add(new HotelData("Hotel Amino",  "2000$", "North Street", "false", 4.7));
        list.add(new HotelData("San Jones",  "2000$", "North Street", "true", 4.0));
        list.add(new HotelData("Halifax Regional Hotel",  "2000$", "North Street", "false", 4.2));
        list.add(new HotelData("Hotel Pearl",  "2000$", "North Street", "true", 3.5));
        list.add(new HotelData("Hotel Amino",  "2000$", "North Street", "false", 7.5));
        list.add(new HotelData("San Jones",  "2000$", "North Street", "false", 5.5));
        list.sort(Comparator.comparing(HotelData::getAvailability).thenComparing(HotelData::getPrice));
        Collections.reverse(list);
        return list;
    }

//    private void getHotelsListsData() {
//        progressBar.setVisibility(View.VISIBLE);
//        Api.getClient().getHotelsLists(new Callback<List<HotelData>>() {
//            @Override
//            public void success(List<HotelData> userListResponses, Response response) {
//                // in this method we will get the response from API
//                userListResponseData = userListResponses;
//
//                // Set up the RecyclerView
//                setupRecyclerView();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                // if error occurs in network transaction then we can get the error in this method.
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }

//    private void setupRecyclerView() {
//        progressBar.setVisibility(View.GONE);
//        RecyclerView recyclerView = view.findViewById(R.id.search_results_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData);
//        recyclerView.setAdapter(hotelListAdapter);
//
//        //Bind the click listener
//        hotelListAdapter.setClickListener(this);
//    }

    @Override
    public void onClick(View view, int position) {
        HotelData hotelListData = userListResponseData.get(position);
        String hotelName = "";
        String hotelPrice = "";
        String hotelAvailability = "";
        String hotelAddress = "";
        double hotelRatings = 0;
        if(hotelListData.getAvailability().equalsIgnoreCase("true")) {
            hotelName = hotelListData.getHotel_name();
            hotelPrice = hotelListData.getPrice();
            hotelAvailability = hotelListData.getAvailability();
            hotelAddress = hotelListData.getAddress();
            hotelRatings = hotelListData.getRatings();
        }

        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putString("hotel price", hotelPrice);
        bundle.putString("hotel availability", hotelAvailability);
        bundle.putString("hotel address", hotelAddress);
        bundle.putString("hotel ratings", String.valueOf(hotelRatings));

//        HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
//        hotelGuestDetailsFragment.setArguments(bundle);
//
//        assert getFragmentManager() != null;
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.remove(HotelsListFragment.this);
//        fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commitAllowingStateLoss();
    }

}
