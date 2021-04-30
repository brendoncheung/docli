import database.Constants;
import database.DocliDatabaseManager;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static picocli.CommandLine.*;

public class Docli {

    public static void main(String[] args) {
        DocliDatabaseManager manager = new DocliDatabaseManager();
        manager.createTable();
    }
}



//        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        Car car = ctx.getBean(Car.class);
//        car.drive();
