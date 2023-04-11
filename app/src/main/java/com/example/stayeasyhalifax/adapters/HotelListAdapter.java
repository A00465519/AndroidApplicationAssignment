package com.example.stayeasyhalifax.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stayeasyhalifax.R;
import com.example.stayeasyhalifax.models.HotelData;
import com.example.stayeasyhalifax.ui.fragments.SearchHomeFragment;
import com.example.stayeasyhalifax.ui.fragments.SearchResultListFragment;
import com.example.stayeasyhalifax.utils.ItemClickListener;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {
    private final List<HotelData> hotelListData;
    private final LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    public interface OnButtonClickListener {
        void onButtonClick(int position);
    }
    private OnButtonClickListener onButtonClickListener;

    //Data gets passed in the constructor
    public HotelListAdapter(Context context, List<HotelData> hotelListData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.hotelListData = hotelListData;
    }

    @NonNull
    @Override
    public HotelListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.search_result_unit_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.ViewHolder holder, int position) {
        String hotelName = hotelListData.get(position).getHotel_name();
        String hotelPrice = hotelListData.get(position).getPrice();
        String hotelAvailability = hotelListData.get(position).getAvailability();
        String hotelAddress = hotelListData.get(position).getAddress();
        double hotelRatings = hotelListData.get(position).getRatings();

        // set up the text
        holder.hotelName.setText(hotelName);
        holder.hotelPrice.setText(hotelPrice);
        holder.hotelAddress.setText(hotelAddress);
        holder.hotelRatings.setText(String.valueOf(hotelRatings));
        if(!hotelAvailability.equalsIgnoreCase("true")) {
            holder.itemView.setBackgroundColor(R.color.disabled);
            holder.bookNowButton.setEnabled(false);
            holder.hotelAvailability.setText(R.string.unavailable_display_text);
            holder.itemView.setClickable(false);
        } else {
            holder.hotelAvailability.setText(R.string.available_display_text);
        }
    }

    @Override
    public int getItemCount() {
        if (hotelListData != null) {
            return hotelListData.size();
        } else {
            return 0;
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.onButtonClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView hotelName, hotelPrice, hotelAvailability, hotelAddress, hotelRatings;
        Button bookNowButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotel_name);
            hotelPrice = itemView.findViewById(R.id.hotel_price);
            hotelAvailability = itemView.findViewById(R.id.hotel_availability);
            hotelAddress = itemView.findViewById(R.id.hotel_address);
            hotelRatings = itemView.findViewById(R.id.hotel_review);
            bookNowButton = itemView.findViewById(R.id.book_now_button);

            itemView.setOnClickListener(this);
            bookNowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onButtonClickListener != null) {
                        int position = getAdapterPosition();
                        onButtonClickListener.onButtonClick(position);
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
}
