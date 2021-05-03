package database;

import entities.Item;
import org.springframework.stereotype.Component;

@Component
public class LocalSqliteRepository implements TodoRepository {

    private final DocliDatabaseManager manager;

    public LocalSqliteRepository(DocliDatabaseManager manager) {
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
