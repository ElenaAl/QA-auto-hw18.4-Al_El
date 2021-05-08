package ru.fintech.qa.homework.utils.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.math.BigInteger;

public class DBHibernateService {


    public final BigInteger count(final String name) {


        SessionFactory sessionFactory = DBClient.getSessionFactory();

        Session session = sessionFactory.openSession();

        BigInteger result = (BigInteger) session.createNativeQuery("select count (*) from " + name).uniqueResult();
        session.close();

        return result;

    }

    public final BigInteger countWhere(final String field, final String table, final String names) {

        SessionFactory sessionFactory = DBClient.getSessionFactory();

        Session session = sessionFactory.openSession();

        BigInteger result = (BigInteger) session.createNativeQuery("select count (\""
                + field + "\") from " + table + " where \"name\" in (" + names + ")").uniqueResult();

        session.close();

        return result;
    }

    public final void insertAnimal(
            final int id, final String nameAnimal, final int age, final String type, final int sex, final int place) {
        SessionFactory sessionFactory = DBClient.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Query query = session.createNativeQuery("INSERT INTO animal (id, \"name\", age, \"type\", sex, place) VALUES( "
                + id + ",'"
                + nameAnimal + "',"
                + age + ","
                + type + ","
                + sex + ","
                + place + ");");

        query.executeUpdate();
        session.getTransaction().commit();

        session.close();

    }

    public final void validationID() {
        int count = 1;

        while (count <= 10) {
            try {
                insertAnimal(count, "Лисенок", 2, "1", 1, 1);
            } catch (ConstraintViolationException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    public final void insertWorkman(final int id, final String nameWorkman, final int age, final String position) {
        SessionFactory sessionFactory = DBClient.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("INSERT INTO workman (id, \"name\", age, \"position\") VALUES("
                + id + ","
                + nameWorkman + ","
                + age + ","
                + position + ");");

        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


    public final void insertPlaces(final int id, final String row, final int placeNum, final String namePlace) {
        SessionFactory sessionFactory = DBClient.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("INSERT INTO places (id, \"row\", place_num, \"name\") VALUES("
                + id + "," + row + "," + placeNum + ",'" + namePlace + "');");

        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
