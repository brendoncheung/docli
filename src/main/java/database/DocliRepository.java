package database;

import entities.Item;

public class DocliRepository {

    private final DocliDatabaseManager manager;

    public DocliRepository(DocliDatabaseManager manager) {
        this.manager = manager;

    }

    public void add(Item item) {

    }

    public Item get() {
        return new Item();
    }

    public void delete(int id) {

    }

    public void update(int id) {

    }

}
