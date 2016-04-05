package grability.com.appability.interfaces;

import grability.com.appability.entities.Category;
import grability.com.appability.persistence.DataManager;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/4/16.
 */
public interface ICategories {
    void onCategoriesLoaded(DataManager.DataOrigin origin, RealmResults<Category> categories);
}
