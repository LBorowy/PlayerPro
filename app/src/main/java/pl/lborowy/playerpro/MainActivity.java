package pl.lborowy.playerpro;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    MediaPlayer dubstep;

    @BindDrawable(android.R.drawable.ic_media_play)
    Drawable playImage;

    @BindDrawable(android.R.drawable.ic_media_pause)
    Drawable pauseImage;
    

    @BindView(R.id.play_pauseButton)
    ImageButton playPauseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        dubstep = MediaPlayer.create(this, R.raw.bensound_dubstep);
        playMusic();



    }

    @OnClick(R.id.play_pauseButton)
    public void playMusic() {
        if (!dubstep.isPlaying()) {
           dubstep.start();
        }
        else {
            dubstep.pause();
        }
        refreshPlayPauseButton();
    }

    @OnClick(R.id.rewindButton)
    public void rewindButton() {
        dubstep.seekTo(0);
    }

    @OnClick(R.id.stopButton)
    public void stopButton() {
        dubstep.pause();
        dubstep.seekTo(0);
        refreshPlayPauseButton();
    }

    private void refreshPlayPauseButton() {
        if (dubstep.isPlaying()) {
            playPauseButton.setImageDrawable(pauseImage);
        }
        else {
            playPauseButton.setImageDrawable(playImage);
        }
    }
}
