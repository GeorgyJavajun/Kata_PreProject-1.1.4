package jm.task.core.jdbc.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
public class Util {
    private static final String USER = "root";
    private static final String PASSWORD = "PisunSQL278";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";

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
        Configuration config = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.username", USER)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.current_session_context_class", "thread");
        return config.buildSessionFactory();
    }
}
//hibernate.connection.driver_class = com.mysql.cj.jdbc.Driver
//hibernate.connection.url = jdbc:mysql://localhost:3306/mysql
//hibernate.connection.username = root
//hibernate.connection.password = PisunSQL278
//hibernate.show_sql = true
//hibernate.dialect = org.hibernate.dialect.MySQLDialect
//hibernate.current_session_context_class = thread