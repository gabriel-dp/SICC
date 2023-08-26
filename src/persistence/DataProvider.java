package src.persistence;

import java.util.HashMap;

import src.model.Entity;

public abstract class DataProvider<T extends Entity> {

    protected HashMap<String, T> data = new HashMap<>();

    public HashMap<String, T> getData() {
        return data;
    }

    public abstract void fetchData();

    public abstract void saveData();

    public abstract void create(T entity);

    public abstract void delete(String id);

}
