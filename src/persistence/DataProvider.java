package src.persistence;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import src.model.Entity;

public abstract class DataProvider<T extends Entity> {

    protected Map<String, T> data = new HashMap<>();

    public List<T> getAllData() {
        return new ArrayList<T>(data.values());
    }

    public T search(String id) {
        return data.get(id);
    }

    public abstract void create(T entity);

    public abstract void delete(String id);

}
