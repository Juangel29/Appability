package grability.com.appability.network;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * Created by juanangelardila on 4/2/16.
 */
public class AppsClient {

    private OkHttpClient client = new OkHttpClient();

    public void getData(final OnCallFinished listener){
        Request request = new Request.Builder()
                .url("https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(listener != null) {
                    listener.onError();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONParser jsonParser = new JSONParser(response.body().string());
                if(listener != null) {
                    listener.onSuccess(jsonParser.getCategories(), jsonParser.getApplications());
                }
            }
        });
    }


    public interface OnCallFinished {
        void onSuccess(JSONArray categories, JSONArray applications);
        void onError();
    }

}
