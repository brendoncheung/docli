package database.repository;

import database.DocliDatabaseManager;
import entities.Item;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocalSqliteRepository implements TodoRepository {

    private final DocliDatabaseManager manager;

    public LocalSqliteRepository(DocliDatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Item item) {
        String s = String.format("INSERT INTO item (DESCRIPTION) VALUES (\"%s\")", item.getDescription());
        manager.executeDMLStatementSync(s);
    }

    @Override
    public Item get() {
        return new Item();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id) {

    }

    @Nullable
    @Override
    public List<Item> getAll() {
        String s = "SELECT * FROM item";
        List<Item> items = new ArrayList<Item>();
        ResultSet set = manager.executeDQLStatementSync(s);
        try {
            while(set.next()) {
                Item item = new Item();
                item.setId(set.getInt("id"));
                item.setDescription(set.getString("description"));
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
