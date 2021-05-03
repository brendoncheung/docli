package database;

import entities.Item;

public interface TodoRepository {
    public void add(Item item);

    public Item get();

    public void delete(int id);

    public void update(int id);

}
