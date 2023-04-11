package com.example.stayeasyhalifax.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.stayeasyhalifax.R;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SearchHomeFragment extends Fragment {

    View view;
    TextView dateRangeTextView;
    Button dateRangeButton, searchButton, retrieveButton, clearButton;
    ConstraintLayout mainLayout;
    EditText nameEditText;
    NumberPicker guestsCountNumber;


    SharedPreferences sharedPreferences;
    public static final String myPreference = "myPref";
    public static final String name = "nameKey";
    public static final String guestsCount = "guestsCountKey";

    String checkInDateString, checkOutDateString, nameString;
    int guestCountInt;
    Long checkInDateLong, checkOutDateLong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.search_home_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainLayout = view.findViewById(R.id.search_home_layout);

        nameEditText = view.findViewById(R.id.name_edit_text);
        dateRangeTextView = view.findViewById(R.id.date_range_text_view);
        guestsCountNumber = view.findViewById(R.id.guests_count_edit_text);
        guestsCountNumber.setValue(0);
        guestsCountNumber.setMinValue(0);
        guestsCountNumber.setMaxValue(10);
        dateRangeButton = view.findViewById(R.id.date_range_button);
        searchButton = view.findViewById(R.id.search_button);
        retrieveButton = view.findViewById(R.id.retrieve_button);
        clearButton = view.findViewById(R.id.clear_button);


        // Saving into shared preferences
        sharedPreferences = requireActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();

        MaterialDatePicker <Pair<Long, Long>> materialDatePicker =
                MaterialDatePicker.
                        Builder.
                            dateRangePicker().
                                setSelection( Pair.create(
                                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                                        MaterialDatePicker.todayInUtcMilliseconds())
                                ).build();


        dateRangeButton.setOnClickListener(v -> {
            materialDatePicker.show(requireActivity().getSupportFragmentManager(), "Tag_picker");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                dateRangeTextView.setText(materialDatePicker.getHeaderText());

                checkInDateLong = selection.first;
                checkOutDateLong = selection.second;

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                checkInDateString = sdf.format(new Date(checkInDateLong));
                checkOutDateString = sdf.format(new Date(checkOutDateLong));

            });
        });

        guestsCountNumber.setOnValueChangedListener((numberPicker, i, i1) -> {
            editor.putString(name, nameEditText.getText().toString());
            editor.putString(guestsCount, String.valueOf(guestsCountNumber.getValue()));
            editor.apply();
        });

        // Search button handling
        searchButton.setOnClickListener(view1 -> {
            try {
                Pair<Long, Long> selection = Objects.requireNonNull(materialDatePicker.getSelection());
                checkInDateLong = selection.first;
                checkOutDateLong = selection.second;

            } catch (NullPointerException ignored) {
            } finally {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                checkInDateString = sdf.format(new Date(checkInDateLong));
                checkOutDateString = sdf.format(new Date(checkOutDateLong));
            }

            guestCountInt = guestsCountNumber.getValue();
            nameString = nameEditText.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("Name", nameString);
            bundle.putString("check in date", checkInDateString);
            bundle.putString("check out date", checkOutDateString);
            bundle.putString("number of guests", String.valueOf(guestCountInt));


//             set Fragment class Arguments
            HotelsListFragment hotelsListFragment = new HotelsListFragment();
            hotelsListFragment.setArguments(bundle);
            assert getFragmentManager() != null;
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_layout, hotelsListFragment);
            fragmentTransaction.remove(HotelSearchFragment.this);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        // Retrieve button handling to fetch sharedPreference Data
        retrieveButton.setOnClickListener(v -> {
            sharedPreferences = requireActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);
            if (sharedPreferences.contains(name)) {
                nameEditText.setText(sharedPreferences.getString(name, ""));
            }
            if (sharedPreferences.contains(guestsCount)) {
                guestsCountNumber.setValue(Integer.parseInt(sharedPreferences.getString(guestsCount, "")));
            }
        });

        //Clear Button Click Listener
        clearButton.setOnClickListener(v -> {
            nameEditText.setText("");
            guestsCountNumber.setValue(0);
        });
    }
}
