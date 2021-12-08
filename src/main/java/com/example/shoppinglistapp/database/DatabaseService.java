package com.example.shoppinglistapp.database;

import com.example.shoppinglistapp.database.DAO.PurchaseDAO;
import com.example.shoppinglistapp.database.entity.Purchase;
import com.example.shoppinglistapp.model.Note;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {
    private final SessionFactory sessionFactory;
    private final Environment environment;

    public DatabaseService(Environment environment) {
        this.environment = environment;
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getMySqlConfiguration() {
//        String connectionString = "jdbc:mysql://localhost:3306/web_application";

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Purchase.class);

        configuration.setProperty("hibernate.connection.url", environment.getProperty("spring.datasource.url"));
        configuration.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.database-platform"));
        configuration.setProperty("hibernate.connection.driver_class", environment.getProperty("spring.datasource.driver-class-name"));
        configuration.setProperty("hibernate.connection.username", environment.getProperty("spring.datasource.username"));
        configuration.setProperty("hibernate.connection.password", environment.getProperty("spring.datasource.password"));
        configuration.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));

        return configuration;
    }

    public List<Purchase> getPurchases(String sessionId) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            PurchaseDAO dao = new PurchaseDAO(session);
            List<Purchase> dataSets = dao.get(sessionId);
            session.close();
            return dataSets;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long addPurchase(Note note) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            PurchaseDAO dao = new PurchaseDAO(session);

            Purchase purchase = new Purchase(note);

            long id = dao.insertPurchase(purchase);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void updatePurchase(Long noteId, Boolean isBought) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            PurchaseDAO dao = new PurchaseDAO(session);

            Purchase purchase = dao.get(noteId);
            purchase.setIsBought(isBought);
            dao.updatePurchase(purchase);

            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void deletePurchase(Long noteId) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            PurchaseDAO dao = new PurchaseDAO(session);

            Purchase purchase = dao.get(noteId);

            dao.deletePurchase(purchase);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
