package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
public class Util {
    private static final String USER = "root";
    private static final String PASSWORD = "password278";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";


    public static SessionFactory getSessionFactory() {
        Configuration config = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.username", USER)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addAnnotatedClass(User.class);
        return config.buildSessionFactory();
    }
}
