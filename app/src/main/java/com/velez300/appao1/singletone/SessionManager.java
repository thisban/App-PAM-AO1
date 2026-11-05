package com.velez300.appao1.singletone;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "user_session";
    private static final String KEY_ACTIVE_SESSION = "active_session";
    private static final String KEY_USER = "username";
    private static SessionManager instance;
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;


    private SessionManager(Context context){
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();;
    }

    public static synchronized SessionManager getInstance(Context context){
        if (instance == null){
            instance = new SessionManager(context);
        }
        return instance;
    }

    public void newLoginSession(String user){
        editor.putBoolean(KEY_ACTIVE_SESSION, true);
        editor.putString(KEY_USER, user);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_ACTIVE_SESSION, false);
    }

    public String getUsername() {return pref.getString(KEY_USER,null);}

    public void logOut(){
        editor.clear();
        editor.apply();
    }
}
