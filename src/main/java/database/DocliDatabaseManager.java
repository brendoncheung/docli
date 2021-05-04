package database;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.sql.*;

@Data
@Component
public class DocliDatabaseManager {

    private Connection connection;
    private final ExceptionHandler handler;

    public DocliDatabaseManager(ExceptionHandler handler) {
        this.handler = handler;
    }

    public ResultSet executeDQLStatementSync(String sql){
        ResultSet res;
        if(connection == null) {
            makeConnection();
        }
        try {
            Statement smt = connection.createStatement();
            res = smt.executeQuery(sql);

        } catch(SQLException e) {
            handler.handleSQLException(e);
            res = null;
        }
        return res;
    }

    public void executeDMLStatementSync(String sql) {
        if(connection == null) {
            makeConnection();
        }
        try {
            Statement smt = connection.createStatement();
            // https://stackoverflow.com/questions/37082904/getting-query-does-not-returns-results-sql-exception
            smt.executeUpdate(sql);

        } catch(SQLException e) {
            handler.handleSQLException(e);
        }
    }

    private void makeConnection() {
        try {
            connection = DriverManager.getConnection(Constants.JDBC_URL);
        } catch (SQLException e) {
            handler.handleSQLException(e);
        }
    }
}
