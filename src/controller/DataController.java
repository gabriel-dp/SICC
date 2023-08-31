package src.controller;

import java.util.ArrayList;

import src.model.Entity;
import src.persistence.*;

public class DataController<T extends Entity> {

    private Class<T> entityClass;
    private DataProvider<T> provider;

    public DataController(Class<T> entityClass) {
        this.entityClass = entityClass;
        provider = new DataProviderFile<T>(entityClass);
        provider.fetchData();
    }

    public T search(String id) {
        return provider.getData().get(id);
    }

    public void create(T entity) throws EntityAlreadyExistsException {
        if (search(entity.getId()) != null) {
            throw new EntityAlreadyExistsException(entityClass.getSimpleName());
        }
        provider.create(entity);
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
