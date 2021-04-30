import commands.AddCommand;
import commands.CommitCommand;
import picocli.CommandLine;
import picocli.CommandLine.RunLast;

import static picocli.CommandLine.*;

@Command
public class GitCommand {
    public static void main(String[] args) {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        Car car = ctx.getBean(Car.class);
//        car.drive();
        CommandLine commandLine = new CommandLine(new GitCommand());
        commandLine.addSubcommand("add", new AddCommand());
        commandLine.addSubcommand("commit", new CommitCommand());

        commandLine.parseWithHandler(new RunLast(), args);
    }

}

