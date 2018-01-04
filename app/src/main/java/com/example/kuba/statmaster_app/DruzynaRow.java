package com.example.kuba.statmaster_app;

import android.app.Activity;
import android.widget.ListView;

/**
 * Created by Kuba on 19.12.2017.
 */

public class DruzynaRow{
    private int id;
    private String nazwa;
    private String trener;

    public DruzynaRow(String nazwa,String trener,int id) {
        this.nazwa = nazwa;
        this.trener = trener;
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getTrener() {
        return trener;
    }

    public int getId(){
        return id;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public void setTrener(String trener) {
        this.trener = trener;
    }
    public void setId(int id) {
        this.id = id;
    }
}
