package persistence;

import java.util.ArrayList;

import model.Entity;

public class DataManager<T extends Entity> {

    private DataProvider<T> provider;

    public DataManager(Class<T> entityClass) {
        provider = new DataProviderFile<T>(entityClass);
    }

    public T search(String id) {
        return provider.getData().get(id);
    }

    public void create(T entity) {
        if (search(entity.getId()) == null) {
            provider.create(entity);
        }
    }

    public void delete(String id) {
        if (search(id) != null) {
            provider.delete(id);
        }
    }

    public ArrayList<T> getAllData() {
        return new ArrayList<T>(provider.getData().values());
    }

}
