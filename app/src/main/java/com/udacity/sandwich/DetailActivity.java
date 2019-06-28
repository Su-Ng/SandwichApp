package com.udacity.sandwich;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwich.model.Sandwich;
import com.udacity.sandwich.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        //Log.i("detail","position :"+position+" json :"+json);

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);



        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        else
        {
          //  Log.d("TAG here",sandwich.getImage());
        }

        populateUI();

        TextView origintv=(TextView) findViewById(R.id.origin_tv);
        origintv.setText(sandwich.getPlaceOfOrigin());

        TextView alsoKnownAstv=(TextView) findViewById(R.id.also_known_tv);

        StringBuilder sb = new StringBuilder();

        String joined = TextUtils.join(", ", sandwich.getAlsoKnownAs());

        String alphaOnlyAlsoKnownAs = joined.replaceAll("[^a-zA-Z ,]","");

        alsoKnownAstv.setText(alphaOnlyAlsoKnownAs);

        TextView descriptiontv=(TextView) findViewById(R.id.description_tv);
        descriptiontv.setText(sandwich.getDescription());

        TextView ingredientstv=(TextView) findViewById(R.id.ingredients_tv);

        StringBuilder sb2 = new StringBuilder();

        String joined2 = TextUtils.join(", ", sandwich.getIngredients());

        String alphaOnlyIngredients = joined2.replaceAll("[^a-zA-Z ,]","");
        String newlineIngredients= alphaOnlyIngredients.replaceAll(", ","\\\r\\\n");

        ingredientstv.setText(newlineIngredients);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
