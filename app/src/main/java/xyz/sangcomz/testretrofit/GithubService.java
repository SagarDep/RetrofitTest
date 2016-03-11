package xyz.sangcomz.testretrofit;

import com.google.gson.JsonElement;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sangcomz on 16. 3. 10.
 */
public interface GithubService {
//    @GET("/users/{username}")
//    Call<UserResponse> getUser(
//            @Path("username") String username
//    );
//
//    @GET("/users/{username}/repos")
//    Call<List<RepositoryResponse>> getUsersRepositories(
//            @Path("username") String username
//    );

    @GET("post/get_posts.php")
    Observable<JsonElement> getPosts(
            @Query("member_srl") String memberSrl,
            @Query("page") String page
    );
}
