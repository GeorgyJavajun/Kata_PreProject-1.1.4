package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;


public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl udh = new UserDaoHibernateImpl();

        udh.createUsersTable();
        udh.saveUser("Ronnie", "Radke", (byte) 38);
        udh.saveUser("Denny", "Worsnop", (byte) 31);
        udh.saveUser("Serj", "Tankian", (byte) 45);
        udh.saveUser("Mitch", "Lucker", (byte) 28);
        udh.removeUserById(3);
        udh.getAllUsers().forEach(System.out::println);
        udh.cleanUsersTable();
        udh.dropUsersTable();

    }
}









//CHANGE THE PASSWORD IN PROPERTIES BEFORE PUSH PROJECT!!!