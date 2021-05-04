import database.Constants;
import database.DocliDatabaseManager;
import database.repository.LocalSqliteRepository;
import database.repository.TodoRepository;
import entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;

@Command
public class Docli {

    @Autowired
    private static DocliDatabaseManager manager;

    @Autowired
    private static TodoRepository repository;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        manager = ctx.getBean(DocliDatabaseManager.class);
        repository = ctx.getBean(LocalSqliteRepository.class);
        initialization();

        CommandLine commandLine = new CommandLine(Docli.class);
        commandLine.execute(args);
    }

    private static void initialization() {
        File config = new File("config.properties");
        if(config.exists()) {
            // user file exists
        } else {
            System.out.println("initializing....");
            try {
                config.createNewFile();
                manager.createDatabaseFile(Constants.WORKING_DIRECTORY);
                manager.executeDQLStatementSync(Constants.CREATE_TABLE_ITEM);
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Command
    public void add(String description) {
        Item item = new Item();
        item.setDescription(description);
        repository.add(item);
    }

    @Command
    public void list() {
        repository.getAll();
    }
}



//        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        Car car = ctx.getBean(Car.class);
//        car.drive();
