package com.example.redi.MyFirstAndroidApp.models.activities.activitiesAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.redi.MyFirstAndroidApp.R;
import com.example.redi.MyFirstAndroidApp.models.entities.SettingItems;

import java.util.List;

/**
 * Created by ReDI on 11/12/2016.
 */

public class SettingItemsAdapter extends RecyclerView.Adapter<SettingItemsAdapter.SettingItemViewHolder> {


    private List<SettingItems> settingItemsList;
    private Context context;


    public SettingItemsAdapter(@NonNull List<SettingItems> settingItemsList, @NonNull Context context) {
        this.settingItemsList = settingItemsList;
        this.context = context;
    }

    @Override
    public SettingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab1_items, parent, false);
        return new SettingItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SettingItemViewHolder holder, int position) {

        final SettingItems settingItem = settingItemsList.get(position);
        holder.sLogoBtn.setImageResource(settingItem.getSlogo());
        holder.sName.setText(settingItem.getSname());
        holder.sSwitch.setChecked(settingItem.getSswitsch());

        holder.sSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingItem.setSswitsch(isChecked);
                buttonView.setText(settingItem.getSswitchtext());

            }
        });
    }

    @Override
    public int getItemCount() {
        return settingItemsList.size();
    }

    static class SettingItemViewHolder extends RecyclerView.ViewHolder {
        ImageButton sLogoBtn;
        TextView sName;
        Switch sSwitch;

        public SettingItemViewHolder(View itemView) {
            super(itemView);
            sLogoBtn = (ImageButton) itemView.findViewById(R.id.img_btn_tab1);
            sName = (TextView) itemView.findViewById(R.id.txt_tab1);
            sSwitch = (Switch) itemView.findViewById(R.id.swi_tab1);

        }
    }

}
