package src.model;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private String id;

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
