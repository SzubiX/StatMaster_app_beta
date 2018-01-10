package com.example.kuba.statmaster_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.util.Log;

import java.util.ArrayList;

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
        private static final int WERSJA_BAZY_DANYCH = 9;

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
        /*-------------------------------------------------*/
        public static final String NAZWA_TABELI_STAT = "BazaStatystyk";
        public static final String ID_STAT = "ID_STAT";
        public static final String STAT_ID_ZAW = "STAT_ID_ZAW";
        public static final String STAT_ID_MECZU = "STAT_ID_MECZU";
        public static final String O_BRAK_POW = "O_BRAK_POW";
        public static final String O_1x1M = "O_1x1M";
        public static final String O_1x1Rz = "O_1x1Rz";
        public static final String O_ZB_WYRZ = "O_ZB_WYRZ";
        public static final String O_PON_AKCJI = "O_PON_AKCJI";
        public static final String O_ZASTAW = "O_ZASTW";
        public static final String A_KONTRA = "A_KONTRA";
        public static final String A_1x1M_CEL ="A_1x1M_CEL";
        public static final String A_1x1M_PROB ="A_1x1M_PROB";
        public static final String A_1x1Rz_CEL ="A_1x1Rz_CEL";
        public static final String A_1x1Rz_PROB ="A_1x1Rz_PROB";
        public static final String A_ZBIORKA = "A_ZBIORKA";
        public static final String A_ZBIORKA_PROB = "A_ZBIORKA_PROB";
        public static final String A_ASYSTA = "A_ASYSTA";
        public static final String A_ASYSTA_PROB = "A_ASYSTA_PROB";
         /*-------------------------------------------------*/
        public static final String NAZWA_TABELI_MECZ = "BazaMecz";
        public static final String ID_MECZU = "ID_MECZU";
        public static final String TYP_MECZU = "TYP_MECZU";
        public static final String ID_STAT_MECZ = "ID_STAT_MECZ";



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

            String createTableStat = "CREATE TABLE " + NAZWA_TABELI_STAT + "(" + ID_STAT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    STAT_ID_ZAW + " INTEGER NOT NULL, " + STAT_ID_MECZU + " INTEGER NOT NULL, " + O_BRAK_POW + " INTEGER DEFAULT 0, " +
                    O_1x1M + " INTEGER DEFAULT 0, " + O_1x1Rz + " INTEGER DEFAULT 0, " + O_ZB_WYRZ + " INTEGER DEFAULT 0, " +
                    O_PON_AKCJI + " INTEGER DEFAULT 0, " + O_ZASTAW + " INTEGER DEFAULT 0, " + A_KONTRA + " INTEGER DEFAULT 0, " +
                    A_1x1M_CEL + " INTEGER DEFAULT 0, " + A_1x1M_PROB + " INTEGER DEFAULT 0, " + A_1x1Rz_CEL + " INTEGER DEFAULT 0, " +
                    A_1x1Rz_PROB + " INTEGER DEFAULT 0, " + A_ZBIORKA + " INTEGER DEFAULT 0, " + A_ZBIORKA_PROB + " INTEGER DEFAULT 0, " +
                    A_ASYSTA + " INTEGER DEFAULT 0, " + A_ASYSTA_PROB + " INTEGER DEFAULT 0, " + "FOREIGN KEY(" + STAT_ID_ZAW + ") REFERENCES " +
                    NAZWA_TABELI_ZAW + "(ID_ZAW) ON DELETE CASCADE);";
            bd.execSQL(createTableStat);

            String createTableMecz = "CREATE TABLE " + NAZWA_TABELI_MECZ + "(" + ID_MECZU + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ID_STAT_MECZ + " INTEGER NOT NULL, " + TYP_MECZU + " TEXT, " +"FOREIGN KEY(" + ID_STAT_MECZ +") REFERENCES " + NAZWA_TABELI_STAT +
                    "(ID_STAT) ON UPDATE CASCADE);";
            bd.execSQL(createTableMecz);
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
            bd.execSQL("DROP TABLE IF EXISTS " + NAZWA_TABELI_STAT);
            bd.execSQL("DROP TABLE IF EXISTS " + NAZWA_TABELI_MECZ);
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
    public boolean dodajMecz(String nazwa) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYP_MECZU, nazwa);
        Log.d(TAG, "Dodano mecz: " + nazwa);

        long result = bd.insert(NAZWA_TABELI_MECZ, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getGameID(String typ){
        SQLiteDatabase bd = this.getWritableDatabase();
        String query = "SELECT " + ID_MECZU + " FROM " + NAZWA_TABELI_MECZ + " WHERE " +
                TYP_MECZU + " = '" + typ + "'";
        Cursor data = bd.rawQuery(query, null);
        return data;
    }
    public Cursor createStat(int id_zaw, String typ){
        SQLiteDatabase bd = this.getWritableDatabase();
        String query = "INSERT INTO " + NAZWA_TABELI_STAT + " (STAT_ID_ZAW, STAT_ID_MECZU) VALUES (" +
                "'" + id_zaw + "'" + "," + "'" + typ +"')";
        Cursor data = bd.rawQuery(query, null);
        return data;
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
        String query = "SELECT " + NAZWA_ZAW  + " FROM " + NAZWA_TABELI_ZAW + " WHERE " +
                TEAM_ID + " ='" + team_id + "'";
        Cursor data = bd.rawQuery(query, null);
        return data;
    }

    public Cursor getPlayersID(String nazwa){
        SQLiteDatabase bd = this.getWritableDatabase();
        String query = "SELECT " + ID_ZAW + " FROM " + NAZWA_TABELI_ZAW + " WHERE " +
                NAZWA_ZAW + " ='" + nazwa + "'";
        Cursor data = bd.rawQuery(query, null);
        return data;
    }

}

