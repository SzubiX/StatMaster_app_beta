package com.example.kuba.statmaster_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 03.01.2018.
 */

public class PlayerListAdapter extends ArrayAdapter<First5_row> {

    private static final String TAG = "PlayerListAdapter";
    private Context mContext;
    int Resource;
    private ArrayList<String> checkedNames;
    private int numberOfChecked;


    public PlayerListAdapter(Context context, int resource, ArrayList<First5_row> objects) {
        super(context, resource, objects);
        mContext = context;
        Resource = resource;
        checkedNames = new ArrayList<>();
        numberOfChecked = 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final String nazwa = getItem(position).getNazwa();
        First5_row first5_row = new First5_row(nazwa);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(Resource, parent, false);

        final CheckedTextView tvNazwa = (CheckedTextView) convertView.findViewById(R.id.PlayerRow);
        tvNazwa.setText(nazwa);
        tvNazwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvNazwa.isChecked()) {
                    tvNazwa.setChecked(false);
                    Log.d(TAG, nazwa + " is Unchecked");
                    checkedNames.remove(nazwa);
                    numberOfChecked--;
                }
                else if(numberOfChecked<5){
                    tvNazwa.setChecked(true);
                    Log.d(TAG, nazwa + " is Checked");
                    checkedNames.add(nazwa);
                    numberOfChecked++;
                }
            }
        });
        return convertView;
    }

    public ArrayList<String> getCheckedNames(){

        return checkedNames;
    }
}