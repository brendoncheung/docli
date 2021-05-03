package database;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.*;

@Data
@Component
public class DocliDatabaseManager {

    private Connection connection;

    public DocliDatabaseManager() {
    }

    public boolean createDatabaseFile(String dir){
        File db = new File(Constants.DATABASE_FILE_NAME);
        boolean res = false;
        try {
            res = db.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public boolean executeStatement(String sql){
        boolean res;
        if(connection == null) {
            makeConnection();
        }
        try {
            Statement smt = connection.createStatement();
            smt.execute(sql);
            res = true;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            res = false;
        }
        return res;
    }

    private void makeConnection() {
        try {
            connection = DriverManager.getConnection(Constants.JDBC_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
