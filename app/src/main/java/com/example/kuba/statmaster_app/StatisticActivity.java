package com.example.kuba.statmaster_app;

import android.content.Intent;
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

    private static final String TAG = "StatisticActivity";
    ArrayList<String> checkedNamesList1;
    DataBaseHelper DaneDrużyn;
    private int SelectedTeamId;
    ListView playersListView;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DaneDrużyn = new DataBaseHelper(this);

        Button[] first5 = new Button[5];
        first5[0] = (Button) findViewById(R.id.Zaw1);
        first5[1] = (Button) findViewById(R.id.Zaw2);
        first5[2] = (Button) findViewById(R.id.Zaw3);
        first5[3] = (Button) findViewById(R.id.Zaw4);
        first5[4] = (Button) findViewById(R.id.Zaw5);

        Intent receivedIntent = getIntent();
        checkedNamesList1 = receivedIntent.getStringArrayListExtra("ListNazwa");
        SelectedTeamId = receivedIntent.getIntExtra("ID", -1);

        playersListView = (ListView) findViewById(R.id.GameList);

        for(i=0; i<5; i++)
        first5[i].setText(checkedNamesList1.get(i));

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,checkedNamesList1);
        playersListView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
