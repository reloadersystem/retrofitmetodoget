package pe.reloadersystem.retrofitjson;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import pe.reloadersystem.retrofitjson.Interface.JsonPlaceHolderApi;
import pe.reloadersystem.retrofitjson.Model.Posts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);
        getPost();
    }

    private void getPost() {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi= retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Posts>> call = jsonPlaceHolderApi.getPots();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }

                List<Posts> postList = response.body();
                //muestra el json

                for(Posts post: postList){

                    String content ="";
                    content += "userId"+ post.getUserId() + "\n";
                    content += "id"+ post.getId() + "\n";
                    content += "title"+ post.getTitle() + "\n";
                    content += "body"+ post.getBody() + "\n\n";
                    mJsonTxtView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                mJsonTxtView.setText(t.getMessage());

            }
        });


    }



}
