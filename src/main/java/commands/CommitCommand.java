package commands;

import static picocli.CommandLine.*;

@Command
public class CommitCommand implements Runnable{
    @Override
    public void run() {
        System.out.println("Commiting files in the staging area, how wonderful?");
    }
}
