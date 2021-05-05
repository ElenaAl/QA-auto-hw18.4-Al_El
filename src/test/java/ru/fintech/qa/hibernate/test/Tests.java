package ru.fintech.qa.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.db.DBHibernateService;
import ru.fintech.qa.homework.utils.db.DBClient;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @BeforeAll
    public static void createDB() {
        BeforeUtils.createData();
    }


    //В таблице public.animal ровно 10 записей
    @Test
    public void insertTest1() {
        SessionFactory sessionFactory = DBClient.getSessionFactory();
        Session session = sessionFactory.openSession();

        BigInteger result = new DBHibernateService().count("animal");
        System.out.println(result);

        BigInteger expected = BigInteger.valueOf(10);
        assertEquals(expected, result);

        session.close();

    }

    // В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно.
    // Если id = от 1 до 10, то тест должен упасть
    @Test
    public void insertTest2() {
        SessionFactory sessionFactory = DBClient.getSessionFactory();
        Session session = sessionFactory.openSession();

        new DBHibernateService().insertAnimal(10, "Лисенок", 2, "1", 1, 1);

        session.close();

    }

    //В таблицу public.workman нельзя добавить строку с name = null. тест должен упасть
    @Test
    public void insertTest3() {
        SessionFactory sessionFactory = DBClient.getSessionFactory();
        Session session = sessionFactory.openSession();

        new DBHibernateService().insertWorkman(7, "null", 22, "2");
       // new DBHibernateService().insertWorkman(7, "'Лисенок'", 22, "2");
        session.close();

    }


    //Если в таблицу public.places добавить еще одну строку, то в ней будет 6 строк
    @Test
    public void insertTest4() {
        SessionFactory sessionFactory = DBClient.getSessionFactory();
        Session session = sessionFactory.openSession();

        new DBHibernateService().insertPlaces(6, "7", 999, "Загон3");
        BigInteger countPlaces = new DBHibernateService().count("places");
        System.out.println(countPlaces);

        BigInteger expected = BigInteger.valueOf(6);

        assertEquals(expected, countPlaces);
        session.close();

    }

    //В таблице public.zoo всего три записи с name 'Центральный', 'Северный', 'Западный'
    @Test
    public void insertTest5() {
        SessionFactory sessionFactory = DBClient.getSessionFactory();
        Session session = sessionFactory.openSession();

        BigInteger result =  new DBHibernateService()
                .countWhere("name", "zoo", "'Центральный', 'Северный', 'Западный'");
        System.out.println(result);

        String expected = "3";
        assertEquals(expected, result.toString());
        session.close();

    }
}
