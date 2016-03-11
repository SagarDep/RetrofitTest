package xyz.sangcomz.testretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String HTTP = "http://";
    public static final String SERVER_HOST = "sangcomz.xyz/open_sns/";
    public static final String URL_GET_POSTS = HTTP + SERVER_HOST + "post/get_posts.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<JsonElement> posts = (Observable<JsonElement>) RestfulAdapter.getInstance().getPosts("14555271234308", "1");

        posts.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<JsonElement>() {
                    @Override
                    public void call(JsonElement jsonElement) {
                        Gson gson = new Gson();
                        String jsonOutput = jsonElement.getAsJsonObject().get("posts").toString();
                        Type listType = new TypeToken<List<Post>>() {
                        }.getType();
                        List<Post> posts = gson.fromJson(jsonOutput, listType);
                    }
                });

//        posts.enqueue(new Callback<JsonElement>() {
//            @Override
//            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//                System.out.println("onResponse :::: " + response.body().toString());
////                JSONObject object = response.body();
////                List<JSONObject> posts = response.body();
//
//
////                System.out.println("object object :::: " + object.toString());
//            }
//
//            @Override
//            public void onFailure(Call<JsonElement> call, Throwable t) {
//                System.out.println("onFailure :::: " + t.toString());
//                System.out.println("onFailure call :::: " + call.toString());
//            }
//        });
//    }

}
}
