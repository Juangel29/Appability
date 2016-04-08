package grability.com.appability.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *
 * Created by juanangelardila on 4/1/16.
 */
public class Category extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String scheme;

    public static String ID_FIELD = "category.id";
    public static String NAME_FIELD = "name";

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
