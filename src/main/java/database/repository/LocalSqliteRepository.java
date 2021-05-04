package database.repository;

import database.DocliDatabaseManager;
import entities.Item;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public List<Item> getAll() {
        String s = "SELECT * FROM item";

        ResultSet set = manager.executeDQLStatementSync(s);
        try {
            while(set.next()) {
                String desc = set.getInt("id") + ": " + set.getString("description");
                System.out.println(desc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
