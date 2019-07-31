package edu.northsouth.smartbin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {

    public static SessionFactory sessionFactory;
    private static HibernateUtil hibernateUtil;
    Session session;

    private HibernateUtil() {

    }

    public static HibernateUtil getInstance() {
        if (hibernateUtil == null) {
            hibernateUtil = new HibernateUtil();
        }
        return hibernateUtil;
    }

    public static synchronized SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        return sessionFactory;
    }

    public Session getSession() {
        session = getSessionFactory().openSession();
        //System.out.println("Code = " + session.hashCode());
        return session;
    }
}
