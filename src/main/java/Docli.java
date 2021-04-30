import picocli.CommandLine;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static picocli.CommandLine.*;

@Command
public class Docli {
    private static final String DATABASE_NAME = "docli_database.db";
    private static final String WORKING_DIRECTORY = System.getProperty("user.dir");

    public static void main(String[] args) {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        Car car = ctx.getBean(Car.class);
//        car.drive();
        System.out.println(WORKING_DIRECTORY);
        CommandLine cmd = new CommandLine(new Docli());
        createTable();
        cmd.execute(args);
    }

    private static void connect() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(String.format("jdbc:sqlite:%s",DATABASE_NAME));
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    private static boolean createDatabase() {
        boolean res = false;
        System.out.println("Opened database successfully");
        return res;
    }

    private static boolean createTable() {

        String url = "jdbc:sqlite:" + DATABASE_NAME;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Command(name = "status", description = "Displays database connection status")
    public void status() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:docli.db");
            System.out.println(c.getMetaData().getDriverName());
            System.out.println(c.getMetaData().getDriverVersion());

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("printing status");
    }
}



