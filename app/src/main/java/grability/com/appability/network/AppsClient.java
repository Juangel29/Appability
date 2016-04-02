package grability.com.appability.network;

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

    public void getData(){
        Request request = new Request.Builder()
                .url("")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                JSONParser jsonParser = new JSONParser(response.body().toString());
                jsonParser.getApplications();
                jsonParser.getCategories();
            }
        });
    }

}
