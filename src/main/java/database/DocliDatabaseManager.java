package database;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.*;

@Data
@Component
public class DocliConnectionManager {

    private Connection connection;

    public DocliConnectionManager() {
        try {
            connection = DriverManager.getConnection(Constants.JDBC_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
