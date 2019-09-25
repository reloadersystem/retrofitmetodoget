package pe.reloadersystem.retrofitjson.Interface;

import java.util.List;

import pe.reloadersystem.retrofitjson.Model.Posts;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Posts>> getPots();

}
