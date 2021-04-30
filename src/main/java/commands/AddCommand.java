package commands;

import picocli.CommandLine;

@CommandLine.Command(name = "add")
public class AddCommand implements Runnable{



    @Override
    public void run() {
        System.out.println("Adding some files to staging area");
    }
}
