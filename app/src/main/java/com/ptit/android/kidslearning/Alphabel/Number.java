package com.ptit.android.kidslearning.Alphabel;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ptit.android.kidslearning.MainControl;
import com.ptit.android.kidslearning.R;

public class Number extends Activity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    ImageView imgv_number_one, imgv_number_two, imgv_number_three, imgv_number_four, imgv_number_five,
            imgv_number_six, imgv_number_seven, imgv_number_eight, imgv_number_nine, imgv_number_ten;
    Button btn_chose_alphabet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        initView();
    }

    public void initView() {
        imgv_number_one = findViewById(R.id.imgv_number_one);
        imgv_number_two = findViewById(R.id.imgv_number_two);
        imgv_number_three = findViewById(R.id.imgv_number_three);
        imgv_number_four = findViewById(R.id.imgv_number_four);
        imgv_number_five = findViewById(R.id.imgv_number_five);
        imgv_number_six = findViewById(R.id.imgv_number_six);
        imgv_number_seven = findViewById(R.id.imgv_number_seven);
        imgv_number_eight = findViewById(R.id.imgv_number_eight);
        imgv_number_nine = findViewById(R.id.imgv_number_nine);
        imgv_number_ten = findViewById(R.id.imgv_number_ten);
        btn_chose_alphabet = findViewById(R.id.btn_chose_alphabet);

        imgv_number_one.setOnClickListener(this);
        imgv_number_two.setOnClickListener(this);
        imgv_number_three.setOnClickListener(this);
        imgv_number_four.setOnClickListener(this);
        imgv_number_five.setOnClickListener(this);
        imgv_number_six.setOnClickListener(this);
        imgv_number_seven.setOnClickListener(this);
        imgv_number_eight.setOnClickListener(this);
        imgv_number_nine.setOnClickListener(this);
        imgv_number_ten.setOnClickListener(this);

        btn_chose_alphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Number.this, AlphabetView.class);
                startActivity(in);
            }
        });
    }

    // click hình ảnh thì phát ra âm thanh
    @Override
    public void onClick(View v) {
        // lựa chọn từng hình ảnh và khi click vào phát ra âm thanh
        switch (v.getId()) {
            case R.id.imgv_number_one:
                mediaPlayer = MediaPlayer.create(this, R.raw.one);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_two:
                mediaPlayer = MediaPlayer.create(this, R.raw.two);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_three:
                mediaPlayer = MediaPlayer.create(this, R.raw.three);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_four:
                mediaPlayer = MediaPlayer.create(this, R.raw.four);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_five:
                mediaPlayer = MediaPlayer.create(this, R.raw.five);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_six:
                mediaPlayer = MediaPlayer.create(this, R.raw.six);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_seven:
                mediaPlayer = MediaPlayer.create(this, R.raw.seven);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_eight:
                mediaPlayer = MediaPlayer.create(this, R.raw.eight);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_nine:
                mediaPlayer = MediaPlayer.create(this, R.raw.nine);
                mediaPlayer.start();
                break;
            case R.id.imgv_number_ten:
                mediaPlayer = MediaPlayer.create(this, R.raw.ten);
                mediaPlayer.start();
                break;
        }
    }

    public void BackHome(View view) {
        Intent in = new Intent(Number.this, MainControl.class);
        startActivity(in);
    }
}