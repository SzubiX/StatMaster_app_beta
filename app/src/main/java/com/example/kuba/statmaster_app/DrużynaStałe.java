package com.example.kuba.statmaster_app;

import android.provider.BaseColumns;

/**
 * Created by Kuba on 18.12.2017.
 */

public class DrużynaStałe {

public interface Drużyny extends BaseColumns{
    public static final String NAZWA_TABELI = "BazaDrużyn";

    public static final String NAZWA_DRUŻYNY = "nazwa";
    public static final String TRENER = "trener";
}
}
