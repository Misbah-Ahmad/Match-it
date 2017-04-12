package com.example.fahimspc.gametest;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * Created by Fahim Chowdhury on 11/22/2016.
 */

public class ScorePreference {

    static final String FILE_NAME = "scoreInfo";
    static final String IS_HIGH_SCORE_SET = "isHighScoreSet";
    static final String HIGH_SCORE = "highScore";
    static final String HIGH_SCORER = "highScorer";
    SharedPreferences sharedPreferences;


    public ScorePreference getLoginPreferences(Context context) {
        return (ScorePreference) context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public ScorePreference(){}

    public ScorePreference(Context context){
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }


    public void setStringPreference(String KEY, String Value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, Value);
        editor.commit();
    }

    public void setBooleanPreferences(String KEY, boolean Value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY, Value);
        editor.commit();
    }

    public void setIntegerPreferences(String KEY, int Value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY, Value);
        editor.commit();
    }

    public void setLongPreferences(String KEY, long Value){
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putLong(KEY, Value);
        editor.commit();
    }

    public String getStringPreferences(String KEY){
        return sharedPreferences.getString(KEY, "none");
    }

    public boolean getBooleanPreferences(String KEY){
        return sharedPreferences.getBoolean(KEY, false);
    }

    public int getIntegerPreferences(String KEY){
        return sharedPreferences.getInt(KEY, -1);
    }

    public long getLongPreferences(String KEY){
        return sharedPreferences.getLong(KEY, -1);
    }




}
