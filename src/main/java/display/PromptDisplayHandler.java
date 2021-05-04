package display;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import entities.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptDisplayHandler {

    public PromptDisplayHandler() {

    }

    public void handlerAddItemDisplay(Item item) {
        String s = String.format("TODO: %s has been added to list", item.getDescription());
        System.out.println(Ansi.colorize(s, Attribute.YELLOW_TEXT()));
    }

    public void display(String s) {
        System.out.println(s);
    }

    public void displayAllItems(List<Item> items) {

        String s1 = String.format("You have %d items on the list: \n", items.size());

        System.out.println(Ansi.colorize(s1, Attribute.YELLOW_TEXT()));

        for(Item item : items) {
            String s2 = String.format("%d: %s", item.getId(), item.getDescription());
            System.out.println(s2);
        }
    }


}
