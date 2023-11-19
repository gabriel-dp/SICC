package src.controller;

import java.util.List;

import src.model.Entity;
import src.persistence.*;

public class DataController<T extends Entity> {

    private Class<T> entityClass;
    private DataProvider<T> provider;

    public DataController(Class<T> entityClass) {
        this.entityClass = entityClass;
        provider = new DataProviderFile<T>(entityClass);
    }

    public List<T> getAllData() {
        return provider.getAllData();
    }

    public T search(String id) {
        return provider.search(id);
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

}
