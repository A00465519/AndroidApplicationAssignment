package com.example.stayeasyhalifax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stayeasyhalifax.R;
import com.example.stayeasyhalifax.adapters.HotelListAdapter;
import com.example.stayeasyhalifax.api.HotelAPI;
import com.example.stayeasyhalifax.models.HotelData;
import com.example.stayeasyhalifax.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultListFragment extends Fragment implements ItemClickListener {

    View view;
    TextView displayInfoTextView;
    ProgressBar progressBar;
    List<HotelData> hotelListResponseData;

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

        getHotelsListsData();
        if(hotelListResponseData == null || hotelListResponseData.size() <=0) {
            hotelListResponseData = initHotelList();
            setupRecyclerView();
        }

        RecyclerView recyclerView = view.findViewById(R.id.search_results_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListResponseData);
        hotelListAdapter.setOnButtonClickListener(position -> {
            // Create a new fragment and pass any necessary data to it
            onClick(view, position);
        });
        recyclerView.setAdapter(hotelListAdapter);
    }

    public ArrayList<HotelData> initHotelList() {
        ArrayList<HotelData> list = new ArrayList<>();

        list.add(new HotelData("Halifax Regional Hotel", "200$", "North Street", 3.2, "true"));
        list.add(new HotelData("Hotel Pearl", "300$", "South Street", 4.2, "true"));
        list.add(new HotelData("Hotel Amino", "100$", "West Street", 4.1, "false"));
        list.add(new HotelData("San Jones", "205$", "Agricola Street", 4.2, "true"));
        list.add(new HotelData("Halifax Public Hotel", "260$", "Windsor Street", 4, "true"));
        list.add(new HotelData("Hotel EasyPeasy", "75$", "London Street", 3.75, "true"));
        list.add(new HotelData("Pembrooke", "1200$", "Inglis Street", 4.5, "false"));
        list.add(new HotelData("Five Seasons", "2000$", "Robie Street", 4.9, "true"));
        list.sort(Comparator.comparing(HotelData::getAvailability).thenComparing(HotelData::getPrice));
        Collections.reverse(list);
        return list;
    }

    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<HotelData>> call = HotelAPI.getClient().getHotelsLists();
        call.enqueue(new Callback<List<HotelData>>() {
            @Override
            public void onResponse(@NonNull Call<List<HotelData>> call, @NonNull Response<List<HotelData>> response) {
                // in this method we will get the response from API
                if (response.isSuccessful()) {
                    hotelListResponseData = response.body();
                    // Set up the RecyclerView
                    setupRecyclerView();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<HotelData>> call, @NonNull Throwable t) {
            // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }

        });
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.search_results_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        HotelData hotelListData = hotelListResponseData.get(position);
        String hotelName = "";
        String hotelPrice = "";
        String hotelAvailability = "";
        String hotelAddress = "";
        double hotelRatings = 0;
        String checkInDate = "";
        String checkOutDate = "";
        String numberOfGuests = "";
        if(hotelListData.getAvailability().equalsIgnoreCase("true")) {
            hotelName = hotelListData.getHotel_name();
            hotelPrice = hotelListData.getPrice();
            assert getArguments() != null;
            checkInDate = getArguments().getString("check in date");
            checkOutDate = getArguments().getString("check out date");
            numberOfGuests = getArguments().getString("number of guests");
        }

        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putString("hotel price", hotelPrice);
        bundle.putString("hotel availability", hotelAvailability);
        bundle.putString("hotel address", hotelAddress);
        bundle.putString("hotel ratings", String.valueOf(hotelRatings));
        bundle.putString("check in date", checkInDate);
        bundle.putString("check out date", checkOutDate);
        bundle.putString("number of guests", numberOfGuests);

        GuestDetailsListFragment guestDetailsListFragment = new GuestDetailsListFragment();
        guestDetailsListFragment.setArguments(bundle);

        assert getFragmentManager() != null;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_layout, guestDetailsListFragment);
        fragmentTransaction.remove(SearchResultListFragment.this);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
