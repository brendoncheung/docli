package database;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.*;

@Data
@Component
public class DocliDatabaseManager {

    private Connection connection;

    public DocliDatabaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(Constants.JDBC_URL);
            System.out.println(Constants.JDBC_URL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createTable() {
        boolean res = false;
        try {
            Statement smt = connection.createStatement();
            String createTableString = "CREATE TABLE IF NOT EXISTS item" +
                    "(id integer PRIMARY KEY AUTOINCREMENT," +
                    "name text(200)," +
                    "description text(200))";

            smt.execute(createTableString);
            res = true;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            res = false;
        }
        return res;

    }
}
