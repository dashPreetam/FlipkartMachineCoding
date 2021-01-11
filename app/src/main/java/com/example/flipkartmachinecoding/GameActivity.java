package com.example.flipkartmachinecoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static com.example.flipkartmachinecoding.Constants.BUNDLE_ARG_ID;
import static com.example.flipkartmachinecoding.Constants.BUNDLE_ARG_IMAGE_URL;
import static com.example.flipkartmachinecoding.Constants.BUNDLE_ARG_NAME;


public class GameActivity extends AppCompatActivity implements RoundFragment.RoundFragmentListener {

    private DataService dataService;
    private RoundFragment fragment;
    private int roundsComplete;
    private int total;
    private int difficulty;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle("Pictionary");

        roundsComplete = 0;
        total = 0;
        difficulty = 3;
        context = getApplicationContext();
        dataService = new DataService();
        dataService.initializeData(this);

        Data data = dataService.getRound(difficulty);

        fragment = new RoundFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_ARG_IMAGE_URL, data.getImageUrl());
        bundle.putString(BUNDLE_ARG_NAME, data.getAnswer());
        bundle.putInt(BUNDLE_ARG_ID, data.getId());
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.game_activity_frame_layout, fragment).commit();
    }

    @Override
    public void onResultSent(int result) {
        roundsComplete++;
        total += result;
        difficulty += result;

        if (roundsComplete <= 5 && difficulty > 0 && total > 0) {
            Data data;
            if (difficulty >= 5)
                difficulty = 5;
            Log.d("TAG Debug", roundsComplete + " " + total + " " + difficulty);
            data = dataService.getRound(5);
            RoundFragment frag = new RoundFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_ARG_IMAGE_URL, data.getImageUrl());
            bundle.putString(BUNDLE_ARG_NAME, data.getAnswer());
            bundle.putInt(BUNDLE_ARG_ID, data.getId());
            frag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.game_activity_frame_layout, frag).commit();
        }
        else {
            Toast.makeText(context, total + "/5", Toast.LENGTH_LONG).show();
        }
    }
}