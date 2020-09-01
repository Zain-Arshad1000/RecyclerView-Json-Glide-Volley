package com.example.jasonparsingwithvolleyintorecyclerview.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.jasonparsingwithvolleyintorecyclerview.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AnimeActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout=null;
    TextView tv_name, tv_studio, tv_categorie, tv_description, tv_rating;
    ImageView img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        // hide the default actionbar
        getSupportActionBar().hide();
        bindIds();
        loadParsedData();

    }

    private void bindIds() {
        tv_name = findViewById(R.id.aa_anime_name);
        tv_studio = findViewById(R.id.aa_studio);
        tv_categorie = findViewById(R.id.aa_categorie) ;
        tv_description = findViewById(R.id.aa_description);
        tv_rating  = findViewById(R.id.aa_rating) ;
        img = findViewById(R.id.aa_thumbnail);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collaspeToolbarId);
    }

    private void loadParsedData() {
        // Recieve data
        String name  = getIntent().getExtras().getString("anime_name");
        String description = getIntent().getExtras().getString("anime_description");
        String studio = getIntent().getExtras().getString("anime_studio") ;
        String category = getIntent().getExtras().getString("anime_category");
        int nb_episode = getIntent().getExtras().getInt("anime_nb_episode") ;
        String rating = getIntent().getExtras().getString("anime_rating") ;
        String image_url = getIntent().getExtras().getString("anime_img") ;
        // setting values to each view

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);

        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(name);
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);



    }
}
