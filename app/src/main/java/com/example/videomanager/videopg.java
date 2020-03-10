package com.example.videomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.videomanager.Database.DBhelper;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class videopg extends YouTubeBaseActivity {
    private final String API_KEY="AIzaSyCQcLOaABYnOr4AFYRHBVt-GCXB7pUzaAs";
    private String VIDEO_CODE="q54MvdxAwQI";
    YouTubePlayerView player;
    YouTubePlayer mp;
    ImageView add;
    ListView vlv;
    DBhelper db;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videopg);

        player = findViewById(R.id.player);
        add = findViewById(R.id.addv);
        vlv = findViewById(R.id.vlv);
        db = new DBhelper(this);
        arrayList = db.getVideoName();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        vlv.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(videopg.this,addvideopg.class);
                startActivity(i);
            }
        });
        vlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String code = db.getvideocode(arrayList.get(i));
                Log.i("SHUB",code);
                VIDEO_CODE = code;
                mp.loadVideo(VIDEO_CODE);
            }

        });
        player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b){
                    youTubePlayer.loadVideo(VIDEO_CODE);
                    mp = youTubePlayer;
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
