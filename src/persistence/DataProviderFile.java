package src.persistence;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import src.model.Entity;

public class DataProviderFile<T extends Entity> extends DataProvider<T> {

    private final String directory = System.getProperty("user.dir");
    private final String extension = ".ser";
    private String filePath;

    public DataProviderFile(Class<T> entityClass) {
        filePath = directory + File.separator + "data" + File.separator + entityClass.getSimpleName() + extension;
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

            @SuppressWarnings("unchecked") // Disable ArrayList<T> cast warning
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
        try {
            Path directoryPath = Paths.get(filePath).getParent();
            if (directoryPath != null && !Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outputStream.writeObject(new ArrayList<T>(data.values()));

            outputStream.close();
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
