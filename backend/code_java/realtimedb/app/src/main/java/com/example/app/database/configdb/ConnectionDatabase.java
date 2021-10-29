package com.example.app.database.configdb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionDatabase {
    public Session getSession(){
        return factory.openSession();
    }
    protected final SessionFactory factory;
    protected ConnectionDatabase(Configuration configuration){
        configuration = configuration.configure();
        configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:"+"app/src/main/resources/chinook.db");
        factory = configuration.buildSessionFactory();
    }
}
