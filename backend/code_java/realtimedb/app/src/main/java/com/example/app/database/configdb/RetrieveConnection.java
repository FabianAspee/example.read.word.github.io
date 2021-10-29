package com.example.app.database.configdb;

public class RetrieveConnection {
    private RetrieveConnection(){}

    public static ConnectionDatabase getInstance(){
        return ConnectionSQLite.getInstance();
    }
}
