package jm.task.core.jdbc.dao;

import com.mysql.cj.util.EscapeTokenizer;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoHibernateImpl implements UserDao {
    private final static String CREATINGSTR = """
         CREATE TABLE IF NOT EXISTS `users` (
         `ID` INT NOT NULL AUTO_INCREMENT,
         `Name` VARCHAR(30) NOT NULL,
         `LastName` VARCHAR(30) NOT NULL,
         `Age` TINYINT(3) NOT NULL,
         PRIMARY KEY (`ID`))""";
    private final static String DROPINGSTR = "DROP TABLE IF EXISTS users";
    private final static String CLEANINGSTR = "TRUNCATE TABLE users";
    private final static String GETINGSTR = "SELECT i FROM User i";
//    private final static String SAVINGSTR = "INSERT INTO users (Name, Lastname, Age) VALUES (?, ?, ?)";
    private final static String REMOVINGSTR = "DELETE User WHERE ID = :id";
    private final SessionFactory sessionFactory = Util.getSessionFactory();



    public UserDaoHibernateImpl() {

    }




    @Override
    public void createUsersTable() {
        Session session = null;
        try  {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(CREATINGSTR).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Oops, something wrong with creating table");
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
        } finally {
            Objects.requireNonNull(session).close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(DROPINGSTR).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Oops, something wrong with dropping");
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
        } finally {
            Objects.requireNonNull(session).close();
        }
    }

    @Override

    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Oops, something wrong with saving user");
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
        } finally {
            Objects.requireNonNull(session).close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery(REMOVINGSTR).setParameter("id", String.valueOf(id)).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Oops, something wrong with removing user");
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
        } finally {
            Objects.requireNonNull(session).close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> list = new ArrayList<>();
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            list = session.createQuery(GETINGSTR, User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Oops, something wrong with getting users");
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
        } finally {
            Objects.requireNonNull(session).close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(CLEANINGSTR).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Oops. something wrong with cleaning");
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
        } finally {
            Objects.requireNonNull(session).close();
        }
    }
}
