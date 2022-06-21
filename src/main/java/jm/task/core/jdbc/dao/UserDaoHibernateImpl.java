package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

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
//    private final static String REMOVINGSTR = "DELETE FROM users WHERE ID = ?";


    public UserDaoHibernateImpl() {

    }


    SessionFactory sessionFactory = Util.getSessionFactory();



    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(CREATINGSTR).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(DROPINGSTR).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override

    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(session.get(User.class, id));
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> list = session.createQuery(GETINGSTR, User.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(CLEANINGSTR).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
}
