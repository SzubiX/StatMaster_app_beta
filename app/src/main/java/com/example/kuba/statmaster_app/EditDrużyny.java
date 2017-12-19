/*package com.example.kuba.statmaster_app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;

import static com.example.kuba.statmaster_app.DrużynaStałe.Drużyny.NAZWA_DRUŻYNY;
import static com.example.kuba.statmaster_app.DrużynaStałe.Drużyny.TRENER;

/**
 * Created by Kuba on 18.12.2017.


public class EditDrużyny extends Activity{
    private DaneDrużyn BazaDruzyn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druzyny);
        BazaDruzyn = new DaneDrużyn(this);
        try {
            dodajDrużyne();
            //Cursor kursor = wezDruzyne();
            //pokazDruzyny(kursor);
        } finally {
            BazaDruzyn.close();
        }
    }

    private void dodajDrużyne() {
        SQLiteDatabase bd = BazaDruzyn.getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(NAZWA_DRUŻYNY, R.id.TeamName);
        wartosci.put(TRENER, R.id.CoachName);

    }
}
*/