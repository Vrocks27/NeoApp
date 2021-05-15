package com.va.neoapp.presentation.homeservices.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.va.neoapp.R;


public class VideosPlayActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private String youtube_id;
    //, video_name;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
//    private View parentLayout;
//    private TextView txtShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_videos);
        readBundle();

        //video_name = intent.getStringExtra("video_name");
        Log.e("youtube_id", "" + youtube_id);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player);
        // youTubePlayerView.initialize(Config.YOUTUBE_API_KEY, this);
        youTubePlayerView.initialize(getString(R.string.api_key), this);


        /*txtShare = findViewById(R.id.txtShare);
        txtShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });*/
    }

    private void readBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("youtube_id")) {
                youtube_id = bundle.getString("youtube_id");
            }
        }
    }

   /* private void share() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, video_name + "\n" + "https://www.youtube.com/watch?v=" + youtube_id +
                "\n\nPlease visit:\n" + "https://play.google.com/store/apps/details?id=com.versatile.aumaujaya&hl=en");
        startActivity(Intent.createChooser(sharingIntent, "Sharing Video"));
    }*/

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.e("youtube_id", "started");
        if (!b) {
            Log.e("youtube_id", "started Val");
            youTubePlayer.loadVideo(youtube_id);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("Network error", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
