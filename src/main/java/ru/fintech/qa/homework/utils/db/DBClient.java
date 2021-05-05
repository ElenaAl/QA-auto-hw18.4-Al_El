package ru.fintech.qa.homework.utils.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.fintech.qa.homework.utils.tables.Animal;
import ru.fintech.qa.homework.utils.tables.Places;
import ru.fintech.qa.homework.utils.tables.Workman;
import ru.fintech.qa.homework.utils.tables.Zoo;


public class DBClient {

    private static SessionFactory sessionFactory = null;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration()
                .configure()
                .addAnnotatedClass(Animal.class)
                .addAnnotatedClass(Workman.class)
                .addAnnotatedClass(Places.class)
                .addAnnotatedClass(Zoo.class)
                .buildSessionFactory();

    }

}
