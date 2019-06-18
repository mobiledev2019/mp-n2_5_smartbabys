package com.ptit.android.kidslearning.Music;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ptit.android.kidslearning.R;
import com.ptit.android.kidslearning.Utils.BusStation;
import com.ptit.android.kidslearning.Utils.ConvertTime;
import com.ptit.android.kidslearning.Utils.Messaage;

import java.util.List;
import java.util.Random;

import app.minimize.com.seek_bar_compat.SeekBarCompat;
import me.relex.circleindicator.CircleIndicator;

public class MusicActivity extends FragmentActivity implements MusicListFragment.mFragmentListener,
        View.OnTouchListener, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {
    /**
     * The pager adapter, which provides the pages to the view pager widget
     */
    PagerAdapter mPageAdapter;

    /**
     * The pager widget
     */
    private ViewPager pager_music;
    LinearLayout activity_music;
    CircleIndicator indicator;
    ImageButton btn_shuffle, btn_prev, btn_play, btn_next, btn_repeat;
    TextView real_time, end_time;

    /**
     * Media Player
     */
    private MediaPlayer mediaPlayer;
    private Handler durationHandler = new Handler();
    SeekBarCompat music_seek;
    private int finalTime = 0;
    private int currentSongIndex = 0;
    private boolean isShuffle = false;
    private boolean isRepeat = false;
    /**
     * The number of pages
     */
    private static final int NUM_PAGE = 2;
    ConvertTime convertTime = new ConvertTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initialize();
        //thiết lập chế độ xoay màn hình
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }



    @Override
    public void onBackPressed() {
        if (pager_music.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager_music.setCurrentItem(pager_music.getCurrentItem() - 1);
        }
    }

    void initialize() {
        mapping();
        mediaPlayer = new MediaPlayer();
        mPageAdapter = new MusicAdapter(getSupportFragmentManager());
        pager_music.setAdapter(mPageAdapter);
        indicator.setViewPager(pager_music);
        //Listen change page
// khi chọn bài hát phát âm thanh ,
        mediaPlayer = new MediaPlayer();
        start(0);
        mediaPlayer.setOnCompletionListener(this);
        music_seek.setOnSeekBarChangeListener(this);
        music_seek.setOnTouchListener(this);

        //Play music
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    Toast.makeText(MusicActivity.this, "Pause", Toast.LENGTH_SHORT).show();
                    pause();
                } else {
                    Toast.makeText(MusicActivity.this, "Play", Toast.LENGTH_SHORT).show();
                    play();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSongIndex < (listSize - 1)) {
                    currentSongIndex += 1;
                    start(currentSongIndex);
                } else {
                    Toast.makeText(MusicActivity.this, "No song to play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSongIndex > 0) {
                    currentSongIndex -= 1;
                    start(currentSongIndex - 1);
                } else {
                    Toast.makeText(MusicActivity.this, "No song to play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShuffle) {
                    isShuffle = false;
                    Toast.makeText(MusicActivity.this, "Shuffle is OFF", Toast.LENGTH_SHORT).show();
                    btn_shuffle.setImageResource(R.drawable.ic_shuffle_white);
                } else {
                    isShuffle = true;
                    Toast.makeText(MusicActivity.this, "Shuffle is ON", Toast.LENGTH_SHORT).show();
                    btn_shuffle.setImageResource(R.drawable.ic_shuffle_green);
                }
            }
        });

        btn_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRepeat) {
                    isRepeat = false;
                    Toast.makeText(MusicActivity.this, "Repeat is OFF", Toast.LENGTH_SHORT).show();
                    btn_repeat.setImageResource(R.drawable.ic_repeat);
                } else {
                    isRepeat = true;
                    Toast.makeText(MusicActivity.this, "Repeat current song", Toast.LENGTH_SHORT).show();
                    btn_repeat.setImageResource(R.drawable.ic_repeat_one);
                }
            }
        });

    }

    void mapping() {
        activity_music = findViewById(R.id.activity_music);
        pager_music = findViewById(R.id.pager_music);
        indicator = findViewById(R.id.indicator);
        real_time = findViewById(R.id.real_time);
        end_time = findViewById(R.id.end_time);
        btn_next = findViewById(R.id.btn_next);
        btn_play = findViewById(R.id.btn_play);
        btn_prev = findViewById(R.id.btn_prev);
        btn_shuffle = findViewById(R.id.btn_shuffle);
        btn_repeat = findViewById(R.id.btn_repeat);
        music_seek = findViewById(R.id.music_Seek);
    }

    /**
     * Start mp3 song
     */
    public void start(int position) {
        getSong(position);
        if (mediaPlayer != null) {
            stop();
        }
        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(name, "raw", getPackageName()));
        Toast.makeText(MusicActivity.this, "Play " + name, Toast.LENGTH_SHORT).show();
        play();
    }

    /**
     * Play mp3 song
     */
    void play() {
        mediaPlayer.start();
        btn_play.setBackgroundResource(R.drawable.ic_pause);
        finalTime = mediaPlayer.getDuration();
        Log.d("Time", "Final Time = " + finalTime);
        music_seek.setMax(finalTime);
        end_time.setText(convertTime.miliToMinute(finalTime));
        durationHandler.postDelayed(updateSeekBarTime, 100);
    }

    /**
     * Pause mp3 song
     */
    void pause() {
        mediaPlayer.pause();
        btn_play.setBackgroundResource(R.drawable.ic_play);
    }

    /**
     * Stop mp3 song
     */
    void stop() {
        btn_play.setBackgroundResource(R.drawable.ic_play);
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    //handler to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        @Override
        public void run() {
            int timeElapsed = mediaPlayer.getCurrentPosition();
            if (timeElapsed > finalTime) {
                durationHandler.removeCallbacks(updateSeekBarTime);
            } else {
                Log.d("Time", "" + timeElapsed);
                music_seek.setProgress(timeElapsed);
                real_time.setText(convertTime.miliToMinute(timeElapsed));
                durationHandler.postDelayed(this, 100);
            }
        }
    };

    /**
     * Get song name
     */
    String name;
    int listSize;

    public String getSong(int position) {
        MusicDataAccess access = new MusicDataAccess(this);
        List<Music> list = access.getMusicList();
        name = list.get(position).getId();
        listSize = list.size();
        Log.d("Size list", String.valueOf(listSize));
        return name;
    }

    @Override
    public void onPositionSelected(int position) {
        Log.d("MyActivity", "Value: " + position);
        currentSongIndex = position;
        BusStation.getBus().post(new Messaage(position));
        start(currentSongIndex);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (mediaPlayer.isPlaying()) {
                int duration = (int) (event.getX() * mediaPlayer.getDuration() / music_seek.getWidth());
                mediaPlayer.seekTo(duration);
            }
        }
        return true;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (isRepeat) {
            start(currentSongIndex);
        } else if (isShuffle) {
            Random rd = new Random();
            currentSongIndex = rd.nextInt((listSize - 1));
            start(currentSongIndex);
        } else {
            if (currentSongIndex < (listSize - 1)) {
                currentSongIndex += 1;
                start(currentSongIndex);
            } else {
                currentSongIndex = 0;
                stop();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        durationHandler.removeCallbacks(updateSeekBarTime);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        durationHandler.removeCallbacks(updateSeekBarTime);
        int currentPosition = music_seek.getProgress();
        mediaPlayer.seekTo(currentPosition);
        durationHandler.postDelayed(updateSeekBarTime, 100);
    }


    /**
     * Music Adapter
     * Bộ chuyển đổi trang âm nhạc
     */
    private class MusicAdapter extends FragmentStatePagerAdapter {

        MusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new MusicListFragment();
            } else if (position == 1) {
                fragment = new LyricsFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGE;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        durationHandler.removeCallbacks(updateSeekBarTime);
        mediaPlayer.release();
    }
}
