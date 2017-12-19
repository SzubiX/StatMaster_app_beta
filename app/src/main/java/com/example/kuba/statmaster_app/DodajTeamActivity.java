package com.example.kuba.statmaster_app;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DodajTeamActivity extends AppCompatActivity {

    private static final String TAG = "DruzynyActivity";

    EditText Team;
    EditText Coach;
    Button DodajBtn;
    DataBaseHelper DaneDrużyn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       Team = (EditText) findViewById(R.id.TeamName);
       Coach = (EditText) findViewById(R.id.CoachName);
        DodajBtn = (Button) findViewById(R.id.AddTeam);  //programowanie przycisku do Dodania Zawodnika
        DaneDrużyn = new DataBaseHelper(this);
        dodajteam();
    }

    public void dodajteam(){
        DodajBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String team = Team.getText().toString();
                String coach = Coach.getText().toString();

                boolean insertTeam = DaneDrużyn.dodajTeam(team, coach);

                if (insertTeam == true) {
                    toastMessage("Pomyślnie zapisano drużynę!");
                }
                else
                    toastMessage("Coś poszło nie tak :( ");
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}