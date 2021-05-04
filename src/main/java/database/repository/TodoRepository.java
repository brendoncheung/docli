package database.repository;

import entities.Item;

import java.util.List;

public interface TodoRepository {
    public void add(Item item);

    public Item get();

    public List<Item> getAll();

    public void delete(int id);

    public void update(int id);

}
