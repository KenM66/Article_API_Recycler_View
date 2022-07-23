package com.example.recyclerviewwebservices;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Article> articles = new ArrayList<>();




    public void run() {
        AppDatabase appDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "articles-db").allowMainThreadQueries().build();


        AndroidNetworking.get("\n" +
                        "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=dae9f0da6e7440789c11dde724b486cc")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {

                        String jsonString = response.toString();

                        //Temporary list to push to database
                        ArrayList<Article> insertList = new ArrayList<>();

                        try {

                            JSONObject obj = new JSONObject(jsonString);
                            JSONArray arr = obj.getJSONArray("articles");

                            for (int i = 0; i < arr.length(); i++) {

                                String title = arr.getJSONObject(i).getString("title");
                                String author = arr.getJSONObject(i).getString("author");

                                Article article = new Article(title, author);

                                insertList.add(article);


                            }

                            // Pushes temporary list to database
                            appDb.articleDao().insertAll(insertList);


                            //sets reference of ArrayList to list retrieved from database
                            articles = (ArrayList<Article>) appDb.articleDao().getArticleList();


                            Log.i("List: ", "" + articles.toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.d("Error: ", "" + anError);
                    }
                });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        run();


    }

    public void display(View view) {  //Method is created to run on push of button so that values can instantiate prior to Recycler view instantiation


        ArticleAdapter adapter = new ArticleAdapter(articles);
        RecyclerView rvArticles = findViewById(R.id.rvArticles);


        rvArticles.setAdapter(adapter);


        rvArticles.setLayoutManager(new LinearLayoutManager(this));

        AndroidNetworking.initialize(getApplicationContext());
    }
}







