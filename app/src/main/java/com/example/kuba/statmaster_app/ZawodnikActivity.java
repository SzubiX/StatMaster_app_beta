package com.example.kuba.statmaster_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZawodnikActivity extends AppCompatActivity {

    private static final String TAG = "ZawodnikActivity";

    EditText PlayerName;
    EditText PlayerNumber;
    Button DodajBtn;
    DataBaseHelper DaneZawodnikow;
    private int selectedTeamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zawodnik);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent receivedIntent = getIntent();
        selectedTeamID = receivedIntent.getIntExtra("ID", -1);
        PlayerName = findViewById(R.id.PlayerName);
        PlayerNumber = findViewById(R.id.PlayersNumber);
        DodajBtn = findViewById(R.id.AddPlayerBtn);
        DaneZawodnikow = new DataBaseHelper(this);
        dodajZawodnika();
        Log.d (TAG, "ID druzyny to "+ selectedTeamID);

    }
        public void dodajZawodnika(){
            DodajBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = PlayerName.getText().toString();
                    String numer = PlayerNumber.getText().toString();
                    int teamId = selectedTeamID;
                    if (name.length() != 0 &&  numer.length() != 0 ) {
                        boolean insertPlayer = DaneZawodnikow.dodajZawodnika(name, numer, teamId);
                        if (insertPlayer == true){
                            toastMessage("Pomyślnie zapisano zawodnika!");
                            Intent intent = new Intent(ZawodnikActivity.this, ZawodnicyListActivity.class);
                            startActivity(intent);
                        }
                        else{
                            toastMessage("Coś poszło nie tak :( ");
                            Intent intent = new Intent(ZawodnikActivity.this, ZawodnicyListActivity.class);
                            startActivity(intent);
                        }
                    } else
                        toastMessage("Pola nie moga być puste!");

                }
            });
        }
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
*/

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
