package com.example.stayeasyhalifax.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.stayeasyhalifax.R;
import com.example.stayeasyhalifax.models.HotelData;
import com.example.stayeasyhalifax.utils.ItemClickListener;

import java.util.List;

public class SearchResultListFragment extends Fragment implements ItemClickListener {

    View view;
    TextView displaInfoTextView;
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
    public void onClick(View view, int position) {

    }

}
