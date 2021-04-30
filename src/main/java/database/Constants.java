package database;

public class Constants {

    public static final String DATABASE_NAME = "docli_database";
    public static final String DATABASE_FILE_NAME = "docli_database.db";
    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    public static final String JDBC_URL = String.format("jdbc:sqlite:/%s/%s", WORKING_DIRECTORY, DATABASE_FILE_NAME);

}
