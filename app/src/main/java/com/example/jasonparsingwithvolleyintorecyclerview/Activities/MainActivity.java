package com.example.jasonparsingwithvolleyintorecyclerview.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jasonparsingwithvolleyintorecyclerview.Adapters.RecyclerViewAdapter;
import com.example.jasonparsingwithvolleyintorecyclerview.Model.Anime;
import com.example.jasonparsingwithvolleyintorecyclerview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL ="https://raw.githubusercontent.com/Zain-Arshad1000/Movies-JSON/master/anime_details.json";
    //To Store Values in a Json array from the resource link given in above Url
    private JsonArrayRequest request;
    //To enqueue and retrieve requests such as URL's with an HTTP method or other parameters
    private RequestQueue requestQueue;
    private List<Anime> animeList;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animeList = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerViewId);
        getSupportActionBar().hide();
        jsonRequest();

    }

    private void jsonRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Anime anime = new Anime() ;
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        anime.setNb_episode(jsonObject.getInt("episode"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setImage_url(jsonObject.getString("img"));
                        animeList.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setRecyclerView(animeList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setRecyclerView(List<Anime> animeList) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,animeList) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}