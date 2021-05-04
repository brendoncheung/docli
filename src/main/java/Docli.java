import database.Constants;
import database.DocliDatabaseManager;
import database.repository.LocalSqliteRepository;
import database.repository.TodoRepository;
import display.PromptDisplayHandler;
import entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;

@Command
public class Docli {

    @Autowired
    private static DocliDatabaseManager manager;

    @Autowired
    private static TodoRepository repository;

    @Autowired
    private static PromptDisplayHandler display;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        manager = ctx.getBean(DocliDatabaseManager.class);
        repository = ctx.getBean(LocalSqliteRepository.class);
        display = ctx.getBean(PromptDisplayHandler.class);
        initialization();

        CommandLine commandLine = new CommandLine(Docli.class);
        commandLine.execute(args);
    }

    private static void initialization() {
        // checking if the user.home has a directory called docli
        File installationDir = new File(Constants.INSTALL_DIRECTORY, "docli");
        if(installationDir.exists()) {
            // user file exists
        } else {
            String s = String.format("Installing files at %s", Constants.INSTALL_DIRECTORY);
            display.display(s);
            installationDir.mkdir();
            try {
                File f = new File(installationDir, "docli_database.db");
                f.createNewFile();
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
            manager.executeDMLStatementSync(Constants.CREATE_TABLE_ITEM);
        }
    }

    @Command(description = "adds an item to the list")
    public void add(String description,
                    @Option(names = "-v") boolean verbose) {
        Item item = new Item();
        item.setDescription(description);
        repository.add(item);
        if(verbose) {
            list();
        } else {
            display.handlerAddItemDisplay(item);
        }
    }

    @Command(description = "list all items")
    public void list() {
        display.displayAllItems(repository.getAll());
    }

    @Command(description = "complete an item")
    public void remove(int id) {
        repository.delete(id);
    }
}
