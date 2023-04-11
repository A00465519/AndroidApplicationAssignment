package com.example.stayeasyhalifax.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stayeasyhalifax.R;
import com.example.stayeasyhalifax.models.GuestData;

import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {


    private final List<GuestData> guestListData;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public GuestListAdapter(Context context, List<GuestData> guestListData) {
        this.guestListData = guestListData;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getItemCount() {
        if (guestListData != null) {
            return guestListData.size();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public GuestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.guest_details_unit_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestListAdapter.ViewHolder holder, int position) {
        String guestNameString = guestListData.get(position).getGuest_name();
        String guestGender = guestListData.get(position).getGender();
        holder.guestDetailsHeader.setText(String.format(context.getString(R.string.guest_deatils_header_text), position+1));
        holder.guestName.setText(guestNameString);
        if (guestGender.equalsIgnoreCase("male")) {
            holder.genderRadioGroup.check(R.id.guest_gender_radio_button_male);
        } else {
            holder.genderRadioGroup.check(R.id.guest_gender_radio_button_female);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView guestDetailsHeader;
        EditText guestName;
        RadioGroup genderRadioGroup;
        RadioButton maleRadioButton, femaleRadioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestDetailsHeader = itemView.findViewById(R.id.guest_detail_header);
            guestName = itemView.findViewById(R.id.guest_details_name);
            genderRadioGroup = itemView.findViewById((R.id.guest_gender_radio_group));
            maleRadioButton = itemView.findViewById((R.id.guest_gender_radio_button_male));
            femaleRadioButton = itemView.findViewById((R.id.guest_gender_radio_button_female));
        }


    }
}
