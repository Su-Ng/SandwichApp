package com.udacity.sandwich.utils;

import android.util.Log;

import com.udacity.sandwich.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {



        try {
            JSONObject jsonObj=new JSONObject(json);
            JSONObject jsonObj2=new JSONObject(jsonObj.getString("name"));
            Log.d("TAG mainName  ",jsonObj2.getString("mainName"));
            Log.d("TAG alsoKnownAs ", jsonObj2.getString("alsoKnownAs"));
            Log.d("TAG placeOfOrigin", jsonObj.getString("placeOfOrigin"));
            Log.d("TAG description", jsonObj.getString("description"));
            Log.d("TAG image", jsonObj.getString("image"));
            Log.d("TAG ingredients",jsonObj.getString("ingredients"));


            String mainName=jsonObj2.getString("mainName");

            List<String> alsoKnownAs=new ArrayList<String>(Arrays.asList( jsonObj2 .getString("alsoKnownAs").split(",")));

            String placeOfOrigin=jsonObj.getString("placeOfOrigin");
            String description=jsonObj.getString("description");
            String image=jsonObj.getString("image");

            List<String> ingredients=new ArrayList<String>(Arrays.asList( jsonObj.getString("ingredients").split(",")));

            Sandwich sw=new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);

            Log.d("TAG sandwich ",sw.getMainName());
            return sw;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }



    }
}
