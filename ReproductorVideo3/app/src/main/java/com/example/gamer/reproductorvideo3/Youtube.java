package com.example.gamer.reproductorvideo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Youtube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    YouTubePlayer player ;
    String url="";
    int tiempovideo=0;
    YouTubePlayerView youTubePlayerView;
    String claveyoutube="AIzaSyCeVVz9o7CiURJ2wa-XXKOEgbWhjSXV_nY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Intent i = getIntent();
        if (i!=null){
            url= i.getStringExtra("videourl");
           // Toast.makeText(this, "ulr"+ url, Toast.LENGTH_SHORT).show();
        }
        //tiempovideo=savedInstanceState.getInt("tiempodelvideo");

        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.yotube_view);

        youTubePlayerView.initialize(claveyoutube, this);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        tiempovideo= player.getCurrentTimeMillis();
        bundle.putInt("tiempodelvideo",tiempovideo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tiempovideo=savedInstanceState.getInt("tiempodelvideo");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restaurado) {
        player= youTubePlayer;

        if(!restaurado){
            if (tiempovideo==0){
                player.loadVideo(url);
            }else{
                player.loadVideo(url,tiempovideo);
            }

            player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
            player.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                @Override
                public void onPlaying() {

                }

                @Override
                public void onPaused() {
                    tiempovideo=player.getCurrentTimeMillis();
                    Log.e("plarlistener","contador "+ tiempovideo);
                }

                @Override
                public void onStopped() {
                    tiempovideo=player.getCurrentTimeMillis();
                    Log.e("plarlistener","contador "+ tiempovideo);
                }

                @Override
                public void onBuffering(boolean b) {

                }

                @Override
                public void onSeekTo(int i) {

                }
            });

        }else{
            player.loadVideo(url,tiempovideo);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        } else{
            String Error= "Error al inicializar youtube"+ youTubeInitializationResult.toString();
            Toast.makeText(getApplication(), Error, Toast.LENGTH_SHORT).show();
        }
    }

    protected  void onActivityResult(int requestcode , int resultcode , int  intentdata){

        if(resultcode==1){
            getyoutubeplayerporvider().initialize(claveyoutube, this);
        }
    }
    protected   YouTubePlayer.Provider getyoutubeplayerporvider(){
        return  youTubePlayerView;
    }
}



