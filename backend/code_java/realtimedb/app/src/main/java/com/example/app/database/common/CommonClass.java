package com.example.app.database.common;

import com.example.app.asynccruddb.contract.CrudOperation;
import com.example.app.asynccruddb.contract.CrudOperationVertx;
import com.example.app.database.configdb.ConnectionDatabase;
import com.example.app.database.configdb.RetrieveConnection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.function.Function;

public abstract class CommonClass implements CrudOperation, CrudOperationVertx {
    protected ConnectionDatabase connectionDatabase = RetrieveConnection.getInstance();
    @Override
    public <B> Optional<B> executionFunction(Function<Session,B> function){
        Transaction transaction = null;
        try (Session session = connectionDatabase.getSession()) {
            transaction = session.beginTransaction();
            B response =function.apply(session);
            transaction.commit();
            return Optional.of(response);
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
