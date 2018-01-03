package com.example.kuba.statmaster_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 19.12.2017.
 */

public class TeamListAdapter extends ArrayAdapter<DruzynaRow>{

    private static final String TAG = "TeamListAdapter";
    private Context mContext;
    int Resource;

    public TeamListAdapter(Context context, int resource, ArrayList<DruzynaRow> objects) {
        super(context, resource, objects);
        mContext = context;
        Resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String nazwa = getItem(position).getNazwa();
        String trener = getItem(position).getTrener();
        int id = getItem(position).getId();
        DruzynaRow druzynaRow = new DruzynaRow(nazwa,trener,id);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(Resource, parent, false);

        TextView tvNazwa = (TextView) convertView.findViewById(R.id.TeamRow);
        TextView tvCoach = (TextView) convertView.findViewById(R.id.CoachRow);
        tvNazwa.setText(nazwa);
        tvCoach.setText(trener);

        return convertView;
    }
}
