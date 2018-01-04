package com.example.kuba.statmaster_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        //tworzenie bazy danych druzyn trzeba dodac id do tabeli z zawodnikami

        public class DataBaseHelper extends SQLiteOpenHelper {
        private static final String TAG = "DatabaseHelper";
        private static final String NAZWA_BAZY_DANYCH = "BazaDrużyn.db";
        private static final int WERSJA_BAZY_DANYCH = 4;

        public static final String NAZWA_TABELI = "BazaDrużyn";
        public static final String ID = "ID";
        public static final String NAZWA_DRUŻYNY = "NAZWA";
        public static final String TRENER = "TRENER";
        /*--------------------------------------------------*/
        public static final String NAZWA_TABELI_ZAW = "BazaZawodników";
        public static final String ID_ZAW = "ID_ZAW";
        public static final String NAZWA_ZAW = "NAZWA_ZAW";
        public static final String NUMER_ZAW = "NUMER_ZAW";
        public static final String TEAM_ID = "TEAM_ID";


        public DataBaseHelper(Context context){
            super(context, NAZWA_BAZY_DANYCH, null, WERSJA_BAZY_DANYCH);
        }

    @Override
    public void onCreate(SQLiteDatabase bd) {
            String createTable = "CREATE TABLE " + NAZWA_TABELI + "(" + ID  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAZWA_DRUŻYNY + " TEXT NOT NULL," + TRENER + " TEXT NOT NULL);";
            bd.execSQL(createTable);

            String createTableZaw = "CREATE TABLE " + NAZWA_TABELI_ZAW + "(" + ID_ZAW  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAZWA_ZAW + " TEXT NOT NULL," + NUMER_ZAW + " TEXT NOT NULL, " + TEAM_ID + " INTEGER NOT NULL, " + "FOREIGN KEY(" + TEAM_ID + ") REFERENCES " +
                    NAZWA_TABELI + "(ID) ON DELETE CASCADE);";
            bd.execSQL(createTableZaw);
    }

    // Enable foreign key constraints
    @Override
    public void onOpen(SQLiteDatabase bd) {
        super.onOpen(bd);
        if (!bd.isReadOnly()) {
            bd.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int staraWersja, int nowaWersja) {
            bd.execSQL("DROP TABLE IF EXISTS " + NAZWA_TABELI);
            bd.execSQL("DROP TABLE IF EXISTS " + NAZWA_TABELI_ZAW);
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

    public boolean dodajZawodnika (String imie, String numer, int teamId){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAZWA_ZAW, imie);
        contentValues.put(NUMER_ZAW, numer);
        contentValues.put(TEAM_ID, teamId);

        Log.d(TAG, "DodajZawodnika: Dodawanie zawodnika " + imie + " " + numer + " teamID: " + teamId + " do " + NAZWA_TABELI_ZAW);

        long result = bd.insert(NAZWA_TABELI_ZAW, null, contentValues);

        if (result  == -1)
            return false;
        else
            return true;
    }

    public Cursor getTeams(){
        SQLiteDatabase bd = this.getWritableDatabase();
        String query = "SELECT * FROM " + NAZWA_TABELI;
        Cursor data = bd.rawQuery(query, null);
        return data;
    }

    public void deleteTeam (int id, String name){
        SQLiteDatabase bd = this.getWritableDatabase();
        String query = "DELETE FROM " + NAZWA_TABELI + " WHERE " + ID +
                " = '" + id + "'" + " AND " + NAZWA_DRUŻYNY + " = '" + name + "'";
        bd.execSQL(query);

    }

    public Cursor getPlayers(int team_id){
        SQLiteDatabase bd = this.getWritableDatabase();
        String query = "SELECT " + NAZWA_ZAW + " FROM " + NAZWA_TABELI_ZAW + " WHERE " +
                TEAM_ID + " ='" + team_id + "'";
        Cursor data = bd.rawQuery(query, null);
        return data;
    }

}

