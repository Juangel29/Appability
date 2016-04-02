package grability.com.appability.models;

import io.realm.RealmObject;

/**
 *
 * Created by juanangelardila on 4/1/16.
 */
public class Category extends RealmObject {
    private String id;
    private String term;
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
