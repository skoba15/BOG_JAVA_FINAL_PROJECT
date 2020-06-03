package ge.bog.finalproject.DBConnection;

import ge.bog.finalproject.models.*;
import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;


public class DBConnection {

    private Session session;
    private SessionFactory sessionFactory;


    private volatile static SessionFactory Instance = null;

    public static SessionFactory getSessionFactory() {
        if (Instance == null) {
            createSessionFactory();
        }
        return Instance;
    }

    private synchronized static void createSessionFactory() {
        if (Instance != null) {
            return;
        }
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(CompanyToCountry.class);
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            Instance = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
