package com.example.kuba.statmaster_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Wybierz5_Activity extends AppCompatActivity {

    private static final String TAG = "First5List";

    private ListView PlayersListView;
    private int selectedTeamID;
    private String selectedTeamName;
    DataBaseHelper DaneDrużyn;
    ArrayList<First5_row> ListData;
    First5_row first5_row;
    PlayerListAdapter adapter;
    private ArrayList<String> checkedNames;
    private String typ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybierz5_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm ");
        df.setTimeZone(TimeZone.getTimeZone("CET"));
        final String currentDate = df.format(c.getTime()); // <- aktualny data/czas

        Intent receivedIntent = getIntent();
        selectedTeamID = receivedIntent.getIntExtra("ID", -1);
        selectedTeamName = receivedIntent.getStringExtra("NAZWA");
        PlayersListView = (ListView) findViewById(R.id.First5_List);
        PlayersListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        DaneDrużyn = new DataBaseHelper(this);
        fillPlayersList();
        checkedNames = adapter.getCheckedNames();
        typ = selectedTeamName + ": " + currentDate;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.dodaj);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Wybierz5_Activity.this, StatisticActivity.class);
                intent.putExtra("ListNazwa", checkedNames);
                intent.putExtra("NAZWA", selectedTeamName);
                intent.putExtra("ID", selectedTeamID);
                intent.putExtra("TYP_MECZU", typ);
                DaneDrużyn.dodajMecz(typ);
                Log.d (TAG, "Dodano mecz: " + typ);
                startActivity(intent);
            }
        });
    }
    private void fillPlayersList() {
        Cursor data = DaneDrużyn.getPlayers(selectedTeamID);
        ListData = new ArrayList<>();
        while (data.moveToNext()) {
            first5_row = new First5_row(data.getString(0));
            ListData.add(first5_row);
        }
        adapter = new PlayerListAdapter(this, R.layout.first5_row, ListData);
        PlayersListView.setAdapter(adapter);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
