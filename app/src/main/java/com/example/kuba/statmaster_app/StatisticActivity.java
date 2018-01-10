package com.example.kuba.statmaster_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StatisticActivity extends AppCompatActivity {

    private static final String TAG = "TAGStatisticActivity";
    ArrayList<String> checkedNamesList;
    DataBaseHelper DaneDrużyn;
    private int SelectedTeamId;
    ListView playersListView;
    int i;
    int id_meczu;
    String typ_meczu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DaneDrużyn = new DataBaseHelper(this);
        Intent receivedIntent = getIntent();
        checkedNamesList = receivedIntent.getStringArrayListExtra("ListNazwa");
        SelectedTeamId = receivedIntent.getIntExtra("ID", -1);
        typ_meczu = receivedIntent.getStringExtra("TYP_MECZU");
       // fillStats();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, checkedNamesList.get(1));
    }

    public void fillStats() {
            Cursor data = DaneDrużyn.getPlayersID(checkedNamesList.get(i));
            ArrayList<Integer> checkedNamesListIDs = new ArrayList<>();
            if (data.moveToFirst()) {
                do{
                checkedNamesListIDs.add(data.getInt(0));
            }
            while (data.moveToNext());

            DaneDrużyn.createStat(checkedNamesListIDs.get(i), typ_meczu);
            Log.d(TAG, "Dodano zawodnika " + checkedNamesListIDs.get(i) + " do meczu: " + typ_meczu);

    }
}

