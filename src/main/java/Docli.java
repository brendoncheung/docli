import database.Constants;
import database.DocliDatabaseManager;
import database.repository.LocalSqliteRepository;
import database.repository.TodoRepository;
import display.PromptDisplay;
import entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Command
public class Docli {

    @Autowired
    private static DocliDatabaseManager manager;

    @Autowired
    private static TodoRepository repository;

    @Autowired
    private static PromptDisplay display;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        manager = ctx.getBean(DocliDatabaseManager.class);
        repository = ctx.getBean(LocalSqliteRepository.class);
        display = ctx.getBean(PromptDisplay.class);
        initialization();

        CommandLine commandLine = new CommandLine(Docli.class);
        commandLine.execute(args);
    }

    private static void initialization() {
        // checking if the user.home has a directory called docli
        File programDir = new File(Constants.INSTALL_DIRECTORY, "docli");
        if(programDir.exists()) {
            // user file exists
        } else {
            String s = String.format("Installing files at %s", Constants.INSTALL_DIRECTORY);
            display.display(s);
            programDir.mkdir();
            try {
                File f = new File(programDir, "docli_database.db");
                f.createNewFile();
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
            manager.executeDMLStatementSync(Constants.CREATE_TABLE_ITEM);
        }
    }

    @Command
    public void add(String description, @Option(names = "-v") boolean verbose) {
        Item item = new Item();
        item.setDescription(description);
        repository.add(item);
        if(verbose) {
            list();
        }
    }

    @Command
    public void list() {
        display.displayAllItems(repository.getAll());
    }

    @Command
    public void remove(int id) {
        repository.delete(id);
    }
}



//        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        Car car = ctx.getBean(Car.class);
//        car.drive();
