package com.example.flipkartmachinecoding;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DataService {

    HashMap<Integer, List<Data>> dataMap;
    List<Integer> usedIndices;


    void initializeData(Context context){
        dataMap = new HashMap<>();
        usedIndices = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(loadJSONFromAsset(context));

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Data data = new Data(
                        jsonObject.getInt("id"),
                        jsonObject.getString("imageUrl"),
                        jsonObject.getString("answer"),
                        jsonObject.getInt("difficulty")

                );

                if(dataMap.containsKey(data.getDifficulty())){
                    List<Data> dataList =dataMap.get(data.getDifficulty());
                    dataList.add(data);
                    dataMap.put(data.getDifficulty(),dataList);
                }
                else{
                    List<Data> dataList = new ArrayList<>();
                    dataList.add(data);
                    dataMap.put(data.getDifficulty(),dataList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public Data getRound(int difficulty){

        List<Data> dataList = dataMap.get(difficulty);
        int randomElementIndex =  getRandom(0, dataList.size() -1);
        usedIndices.add(randomElementIndex);

        return dataList.get(randomElementIndex);

    }

    int getRandom(int low, int high){
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        while(usedIndices.contains(result))
            result = r.nextInt(high-low) + low;
        return result;
    }

}
