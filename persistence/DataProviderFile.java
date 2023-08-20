package persistence;

import java.io.*;
import java.util.ArrayList;

import model.Entity;

@SuppressWarnings("unchecked")
public class DataProviderFile<T extends Entity> extends DataProvider<T> {

    private String filePath;
    private final String extension = ".ser";

    public DataProviderFile(Class<T> entityClass) {
        filePath = "data/" + entityClass.getSimpleName() + extension;
        fetchData();
    }

    public void fetchData() {
        data.clear();
        try {
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new FileNotFoundException();
            }

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            ArrayList<T> dataArray = (ArrayList<T>) inputStream.readObject();
            for (T element : dataArray) {
                data.put(element.getId(), element);
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            saveData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(data.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create(T entity) {
        data.put(entity.getId(), entity);
        saveData();
    }

    public void delete(String id) {
        data.remove(id);
        saveData();
    }

}
