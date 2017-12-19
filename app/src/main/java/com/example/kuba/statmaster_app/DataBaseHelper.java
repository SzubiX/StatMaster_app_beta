package com.example.kuba.statmaster_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.example.kuba.statmaster_app.DrużynaStałe.Drużyny.NAZWA_DRUŻYNY;
import static com.example.kuba.statmaster_app.DrużynaStałe.Drużyny.NAZWA_TABELI;
import static com.example.kuba.statmaster_app.DrużynaStałe.Drużyny.TRENER;

/**
 * Created by Kuba on 18.12.2017.
 */

        public class DataBaseHelper extends SQLiteOpenHelper {
        private static final String TAG = "DatabaseHelper";
        private static final String NAZWA_BAZY_DANYCH = "BazaDrużyn.db";
        private static final int WERSJA_BAZY_DANYCH = 2;

        public static final String ID = "ID";
        public static final String NAZWA_TABELI = "BazaDrużyn";
        public static final String NAZWA_DRUŻYNY = "NAZWA";
        public static final String TRENER = "TRENER";

        public DataBaseHelper(Context context){
            super(context, NAZWA_BAZY_DANYCH, null, WERSJA_BAZY_DANYCH);
        }

    @Override
    public void onCreate(SQLiteDatabase bd) {
            String createTable = "CREATE TABLE " + NAZWA_TABELI + "(" + ID  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAZWA_DRUŻYNY + " TEXT NOT NULL," + TRENER + " TEXT NOT NULL);";
            bd.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int staraWersja, int nowaWersja) {
            bd.execSQL("DROP TABLE IF EXISTS " + NAZWA_TABELI);
            onCreate(bd);

    }
    public boolean dodajTeam(String team, String coach){
            SQLiteDatabase bd = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAZWA_DRUŻYNY, team);
            contentValues.put(TRENER, coach);
            Log.d(TAG, "DodajTeam: Dodawanie " + team + " " + coach + " do " + NAZWA_TABELI);

            long result = bd.insert(NAZWA_TABELI, null, contentValues);

            if (result  == -1)
                return false;
                else
                    return true;


    }


}

