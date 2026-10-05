package com.velez300.appao1.singletone;

public class SessionManager {

    private static SessionManager instance;
    private boolean isLogged = false;

    private SessionManager(){}

    public static synchronized SessionManager getInstance(){
        if (instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(){
        this.isLogged = true;
    }

    public boolean isLoggedIn(){
        return this.isLogged;
    }
}
