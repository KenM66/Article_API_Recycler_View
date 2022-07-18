package com.example.recyclerviewwebservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;




import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Article> articles= new ArrayList<>();

    //Hardcoded articles for testing purposes.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        ArticleAdapter adapter= new ArticleAdapter(articles);
        RecyclerView rvArticles=  findViewById(R.id.rvArticles);




        rvArticles.setAdapter(adapter);


        rvArticles.setLayoutManager(new LinearLayoutManager(this));

       AndroidNetworking.initialize(getApplicationContext());

       AndroidNetworking.get("\n" +
               "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=dae9f0da6e7440789c11dde724b486cc")
               .build().getAsJSONObject(new JSONObjectRequestListener() {
                   @Override
                   public void onResponse(JSONObject response) {
                       //Log.d("Response","Response: "+ response.toString());


                       //Create a string for the JSON text.
                       String jsonString= response.toString();
                       try {

                           //Create a JSON object for JSON string
                           JSONObject obj= new JSONObject(jsonString);


                           //Create a JSON array for the articles
                           JSONArray arr= obj.getJSONArray("articles");


                           //Loop through the JSON array, get the title and author from each element, and create a article object with those strings as arguments in the constructor
                           for(int i=0; i<arr.length(); i++){
                               Article article= new Article(arr.getJSONObject(i).getString("title"), arr.getJSONObject(i).getString("author"));
                               articles.add(article);
                           }

                           Log.d("Articles", articles.toString());

                           //After initialization, the adapter will need to be notified of the change in state when articles object is changed.
                           adapter.notifyDataSetChanged();




                       } catch (JSONException e) {
                           Log.d("Error:", "This did not work!");
                           e.printStackTrace();
                       }



                   }


                   @Override
                   public void onError(ANError anError) {
                       Log.d("Error", "Error was: "+anError);
                   }
               });
//




    }


}