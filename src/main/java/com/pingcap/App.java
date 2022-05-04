package com.pingcap;

import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://127.0.0.1:4000/test?user=root");
        JdbcConnection connection = (JdbcConnection)mysqlDataSource.getConnection();
        System.out.println("Version:" + connection.getServerVersion());
        System.out.println("Major:" + connection.getServerVersion().getMajor());
        System.out.println("Minor:" + connection.getServerVersion().getMinor());
        System.out.println("SubMinor:" + connection.getServerVersion().getSubminor());

        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://127.0.0.1:4000/test", "root", null)
                .load();

        // Start the migration
        flyway.migrate();
    }
}
