package entities;

import lombok.Data;

@Data
public class Item {
    private String title;
    private String description;
    private int priority;
}
