package grability.com.appability.network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by juanangelardila on 4/2/16.
 */
public class JSONParser {

    private String response;
    private Set <String> categoriesIds = new HashSet<>();
    private JSONArray categories = new JSONArray();
    private JSONArray applications = new JSONArray();

    public JSONParser(String response) {
        this.response = response;
        processResponse();
    }

    private void processResponse () {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            if(jsonResponse.has(Params.FEED) && jsonResponse.getJSONObject(Params.FEED).has(Params.ENTRY)) {
                JSONArray jsonEntries = jsonResponse.getJSONObject(Params.FEED).getJSONArray(Params.ENTRY);

                for(int i = 0; i < jsonEntries.length(); i ++) {
                    JSONObject item = jsonEntries.getJSONObject(i);
                    String name = item.getJSONObject(Params.APP_NAME).getString(Params.LABEL);
                    String summary = item.getJSONObject(Params.SUMMARY).getString(Params.LABEL);
                    String rights = item.getJSONObject(Params.RIGHTS).getString(Params.LABEL);
                    String title = item.getJSONObject(Params.TITLE).getString(Params.LABEL);
                    String id = item.getJSONObject(Params.ID).getJSONObject(Params.ATTRIBUTES).getString(Params.IM_ID);
                    String artist = item.getJSONObject(Params.ARTIST).getString(Params.LABEL);
                    String releaseDate = item.getJSONObject(Params.RELEASE_DATE).getString(Params.LABEL);
                    String imageUrl = "";

                    JSONArray images = item.getJSONArray(Params.IMAGE);
                    if (images.length() > 0) {
                        JSONObject jsonImage = images.getJSONObject(images.length() - 1);
                        imageUrl = jsonImage.getString(Params.LABEL);
                    }

                    JSONObject jsonCategory = item.getJSONObject(Params.CATEGORY);
                    JSONObject jsonCategoryAttributes = jsonCategory.getJSONObject(Params.ATTRIBUTES);
                    String catId = jsonCategoryAttributes.getString(Params.IM_ID);
                    String catName = jsonCategoryAttributes.getString(Params.LABEL);
                    String catScheme = jsonCategoryAttributes.getString(Params.SCHEME);

                    JSONObject application = new JSONObject();
                    application.put("name", name);
                    application.put("summary", summary);
                    application.put("rights", rights);
                    application.put("title", title);
                    application.put("id", id);
                    application.put("imageUrl", imageUrl);
                    application.put("artist", artist);
                    application.put("releaseDate", releaseDate);

                    JSONObject category = new JSONObject();
                    category.put("id", catId);
                    category.put("name", catName);
                    category.put("scheme", catScheme);

                    application.put("category", category);

                    processCategory(category);
                    applications.put(application);
                }
            }

        } catch (JSONException e) {
            Log.e("TAG", e.getMessage());
        }
    }

    private void processCategory (JSONObject category) {
        try {
            String id = category.getString("id");
            if(!categoriesIds.contains(id)) {
                categoriesIds.add(id);
                categories.put(category);
            }
        } catch (JSONException e) {
            Log.e("TAG", e.getMessage());
        }
    }

    public JSONArray getCategories() {
        return categories;
    }

    public JSONArray getApplications() {
        return applications;
    }
}
