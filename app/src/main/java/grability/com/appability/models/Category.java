package grability.com.appability.models;

import io.realm.RealmObject;

/**
 *
 * Created by juanangelardila on 4/1/16.
 */
public class Category extends RealmObject {

    private String id;
    private String name;
    private String scheme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
