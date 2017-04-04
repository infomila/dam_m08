package net.iesmila.e3_video;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class MainActivity extends
        AppCompatActivity  implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private SurfaceView mSrfVideo;
    private SurfaceHolder mHolder;
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSrfVideo = (SurfaceView) findViewById(R.id.srfVideo);
        mHolder = mSrfVideo.getHolder();
        mHolder.addCallback(this);
        mPlayer = new MediaPlayer();

        String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        try {
            mPlayer.setDataSource(vidAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mPlayer.setDisplay(mHolder);
        mPlayer.setOnPreparedListener(this);
        mPlayer.prepareAsync();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
