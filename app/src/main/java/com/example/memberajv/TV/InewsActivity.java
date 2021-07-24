package com.example.memberajv.TV;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memberajv.DeveloperKey;
import com.example.memberajv.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class InewsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inews);

        playerView = findViewById(R.id.ytInews);
        playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {


        if (!b) {
            player.loadVideo("trMCYu2qTqE");
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
            player.setFullscreen(true);

        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
