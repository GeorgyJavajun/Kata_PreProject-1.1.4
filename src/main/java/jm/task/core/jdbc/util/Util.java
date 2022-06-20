package jm.task.core.jdbc.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
public class Util {
    private static final String USER = "root";
    private static final String PASSWORD = "PisunSQL278";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static SessionFactory sessionFactory;

    public static Connection getConnect() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection success");
        } catch (SQLException | ClassNotFoundException  e) {
            System.out.println("Connection fail");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        sessionFactory = new Configuration()
                .buildSessionFactory();

        return sessionFactory;
    }
}
