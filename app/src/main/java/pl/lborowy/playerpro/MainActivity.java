package pl.lborowy.playerpro;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SeekBar;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer dubstep;

    @BindDrawable(android.R.drawable.ic_media_play)
    Drawable playImage;

    @BindDrawable(android.R.drawable.ic_media_pause)
    Drawable pauseImage;

    @BindView(R.id.volumeSeekBar)
    SeekBar volumeSeekBar;

    @BindView(R.id.play_pauseButton)
    ImageButton playPauseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dubstep = MediaPlayer.create(this, R.raw.bensound_dubstep);
        initVolumeSeekBar();
        prepareVolumeSeekBarListener();
        playMusic();

    }

    private void initVolumeSeekBar() {
        dubstep.setVolume(1,1);
        volumeSeekBar.setProgress(100);
    }

    private void prepareVolumeSeekBarListener() {
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("SeekBar", "progress : " + progress);
                float volume = (float) progress / 100;
                Log.d("SeekBar", "volume : " + volume);
                dubstep.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dubstep.isPlaying())
            dubstep.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!dubstep.isPlaying())
            dubstep.start();

        refreshPlayPauseButton();
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
