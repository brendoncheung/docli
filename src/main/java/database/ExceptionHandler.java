package database;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

@Component
public class ExceptionHandler {

    public void handleSQLException(SQLException e) {
        System.out.println(e.getMessage());
    }

    public void handleIOException(IOException e) {
        System.out.println(e.getMessage());
    }

}
