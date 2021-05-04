package display;

import entities.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptDisplay {

    public PromptDisplay() {

    }

    public void display(String s) {
        System.out.println(s);
    }

    public void displayAllItems(List<Item> items) {
        for(Item item : items) {
            String s = String.format("%d: %s", item.getId(), item.getDescription());
            System.out.println(s);
        }
    }


}
