package com.example.kuba.statmaster_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 03.01.2018.
 */

public class PlayerListAdapter extends ArrayAdapter<First5_row> {
    private Context mContext;
    int Resource;

    public PlayerListAdapter(Context context, int resource, ArrayList<First5_row> objects) {
        super(context, resource, objects);
        mContext = context;
        Resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String nazwa = getItem(position).getNazwa();
        First5_row first5_row = new First5_row(nazwa);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(Resource, parent, false);

        final CheckedTextView tvNazwa = (CheckedTextView) convertView.findViewById(R.id.PlayerRow);
        tvNazwa.setText(nazwa);
        tvNazwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvNazwa.isChecked())
                    tvNazwa.setChecked(false);
                else
                    tvNazwa.setChecked(true);
            }
        });
        return convertView;
    }
}
