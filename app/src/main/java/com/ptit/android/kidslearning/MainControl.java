package com.ptit.android.kidslearning;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.ptit.android.kidslearning.Alphabel.AlphabetView;
import com.ptit.android.kidslearning.Game.GameActivity;
import com.ptit.android.kidslearning.Music.MusicActivity;
import com.ptit.android.kidslearning.Topic.TopicView;
import com.ptit.android.kidslearning.Vocabulary.Vocabulary_Menu;

public class MainControl extends AppCompatActivity {
    Button btnAlphabet, btnVocab, btnSongs, btnGame;
    MediaPlayer mPlayer;
    boolean isSound;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_control);
        Initialize();
        //thiết lập chế độ xoay màn hình
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        mPlayer.release();
    }

    void Initialize() {
        mapping();
        btnAlphabet.setOnClickListener(new Event());
        btnVocab.setOnClickListener(new Event());
        btnGame.setOnClickListener(new Event());
        btnSongs.setOnClickListener(new Event());
    }

    void mapping() {
        btnAlphabet = findViewById(R.id.btnAlphabet);
        btnVocab = findViewById(R.id.btnVocabulary);
        btnSongs = findViewById(R.id.btnSongs);
        btnGame = findViewById(R.id.btnGame);
    }

    /**
     * set sound track
     */
    private void soundtrack() {
        mPlayer = MediaPlayer.create(MainControl.this, R.raw.music_bkg);
        mPlayer.start();
        mPlayer.setLooping(true);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    };

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Set View
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_setting, null);
        //Mapping
        Switch switch_sound = v.findViewById(R.id.switch_sound);
        switch_sound.setChecked(isSound);
        //Event
        switch_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveStatus(isChecked);
            }
        });
        //set builder for dialog
        builder.setTitle(R.string.tittle_setting)
                .setView(v)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }

    /**
     * Share status
     */
    private void saveStatus(boolean status) {
        preferences = getSharedPreferences("status", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isSound", status);
        editor.commit();
    }

//    private void restoreStatus() {
//        preferences = getSharedPreferences("status", MODE_PRIVATE);
//        isSound = preferences.getBoolean("isSound", false);
//    } if (isSound) {
//            soundtrack();
//        } else
//
////            mPlayer.release();
//        //    Toast.makeText(getApplicationContext(),"Loi Mediaplayer",Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        saveStatus(isSound);
//        mPlayer.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //restoreStatus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPlayer.release();
    }

    private class Event implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent in = null;
            switch (v.getId()) {
                case R.id.btnAlphabet:
                    in = new Intent(MainControl.this, AlphabetView.class);
                    break;
                case R.id.btnVocabulary:
                    in = new Intent(MainControl.this, Vocabulary_Menu.class);
                    break;
                case R.id.btnSongs:
                    in = new Intent(MainControl.this, MusicActivity.class);
                    break;
                case R.id.btnGame:
                    in = new Intent(MainControl.this, GameActivity.class);
                    break;
            }
            startActivity(in);
        }
    }
}