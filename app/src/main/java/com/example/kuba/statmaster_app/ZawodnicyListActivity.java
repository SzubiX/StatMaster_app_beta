package com.example.kuba.statmaster_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ZawodnicyListActivity extends AppCompatActivity {

    private static final String TAG = "ZawodnicyListActivity";

    DataBaseHelper DaneDrużyn;
    private int selectedTeamID;
    private String selectedTeamName;
    private String selectedCoachName;
    private TextView TeamName, CoachName;
    private ListView PlayersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zawodnicy_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TeamName = (TextView) findViewById(R.id.TeamName);
        CoachName = (TextView) findViewById(R.id.CoachName);
        PlayersListView = (ListView) findViewById(R.id.PlayersList);

        Intent receivedIntent = getIntent();
        selectedTeamID = receivedIntent.getIntExtra("ID",-1);
        selectedTeamName = receivedIntent.getStringExtra("NAZWA");
        selectedCoachName = receivedIntent.getStringExtra("TRENER");
        TeamName.setText(selectedTeamName);
        CoachName.setText(selectedCoachName);
        DaneDrużyn = new DataBaseHelper(this);
        fillPlayersList();
        //Log.d (TAG, "ID druzyny to "+ selectedTeamID);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZawodnicyListActivity.this, ZawodnikActivity.class);
                intent.putExtra("ID", selectedTeamID );
                intent.putExtra("NAZWA", selectedTeamName);
                intent.putExtra("TRENER", selectedCoachName);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void fillPlayersList(){
        Log.d (TAG, "Wypełnianie zawodników na listView");
        Cursor data = DaneDrużyn.getPlayers(selectedTeamID);
        ArrayList<String> PlayersList = new ArrayList<>();
        while (data.moveToNext()){
            PlayersList.add(data.getString(0));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, PlayersList);
        PlayersListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zawodnicylist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_deleteTeam) {
            DaneDrużyn.deleteTeam(selectedTeamID,selectedTeamName);
            toastMessage("Usunięto drużynę z bazy danych");
            Intent intent = new Intent(ZawodnicyListActivity.this, DruzynyActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
