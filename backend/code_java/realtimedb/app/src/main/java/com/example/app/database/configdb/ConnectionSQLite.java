package com.example.app.database.configdb;

import org.hibernate.cfg.Configuration;

public class ConnectionSQLite extends ConnectionDatabase{
    private static class ConnectionSQLiteImpl{
        static final ConnectionDatabase instance = new ConnectionSQLite();
    }

    private ConnectionSQLite(){
        super(new Configuration());
    }

    public static ConnectionDatabase getInstance() {
        return ConnectionSQLiteImpl.instance;
    }
}
