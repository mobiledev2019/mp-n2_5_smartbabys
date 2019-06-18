package com.ptit.android.kidslearning.Alphabel;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.android.kidslearning.MainControl;
import com.ptit.android.kidslearning.R;

import java.util.List;

public class AlphabetView extends Activity {
    // hiển view và xử lý dữ liệu
    Button img_Number;
    private int i = 0;
    AlphatbetDataAccess alphatbetDataAccess;
    ImageView imgAlphabet, imgExample;
    TextView txtExample;
    Button btnNext, btnBack;
    List<Alphabet> listAlphabet;
    MediaPlayer mediaPlayer;
    private int id_imgAlphabet, id_imgExample, id_soundAlphabet, id_soundExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        initView();
        setView();
        StartAnimRight();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listAlphabet.size() - 1 != i) {
                    i++;
                    setView();
                    StartAnimRight();
                } else {
                    i = 0;
                    setView();
                    StartAnimRight();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    i--;
                    setView();
                    StartAnimLeft();
                } else {
                    i = listAlphabet.size() - 1;
                    setView();
                    StartAnimLeft();
                }
            }
        });
    }

    public void initView() {
        imgAlphabet = findViewById(R.id.imgv_alphabet_chucai);
        imgExample = findViewById(R.id.imgv_alphabet_ex);
        txtExample = findViewById(R.id.txt_alphabet_ex);
        btnNext = findViewById(R.id.btn_alphabet_next);
        btnBack = findViewById(R.id.btn_alphabet_prev);
        img_Number = findViewById(R.id.btn_alphabet_choose_number);

        alphatbetDataAccess = new AlphatbetDataAccess(this);
        listAlphabet = alphatbetDataAccess.getListAlphabet();
//
    }

    public void setView() {
        id_imgAlphabet = getResources().getIdentifier("com.ptit.android.kidslearning:drawable/" + listAlphabet.get(i).getImage_Alphabet(), null, null);
        id_imgExample = getResources().getIdentifier("com.ptit.android.kidslearning:drawable/" + listAlphabet.get(i).getImageExample(), null, null);
        txtExample.setText(listAlphabet.get(i).getNameExample());
        Typeface typeface = Typeface.createFromAsset(getAssets(), "NimbusRomNo9L_RegIta.otf");
        txtExample.setTypeface(typeface);
        imgAlphabet.setImageResource(id_imgAlphabet);
        imgExample.setImageResource(id_imgExample);
        imgAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_soundAlphabet = getResources().getIdentifier(listAlphabet.get(i).getSound_Alphabet(), "raw", getPackageName());
                mediaPlayer = MediaPlayer.create(AlphabetView.this, id_soundAlphabet);
                mediaPlayer.start();
            }
        });
        imgExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_soundExample = getResources().getIdentifier(listAlphabet.get(i).getSoundExample(), "raw", getPackageName());
                mediaPlayer = MediaPlayer.create(AlphabetView.this, id_soundExample);
                mediaPlayer.start();
            }
        });
        img_Number.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AlphabetView.this, Number.class);
                startActivity(in);
            }
        });
    }

    private void StartAnimRight() {
        Animation anim = AnimationUtils.loadAnimation(AlphabetView.this, R.anim.slide_in_right);

        txtExample.setAnimation(anim);
        imgAlphabet.setAnimation(anim);
        imgExample.setAnimation(anim);

        anim.start();
    }

    private void StartAnimLeft() {
        Animation anim = AnimationUtils.loadAnimation(AlphabetView.this, R.anim.slide_in_left);

        txtExample.setAnimation(anim);
        imgAlphabet.setAnimation(anim);
        imgExample.setAnimation(anim);

        anim.start();
    }

    public void BackHome(View view) {
        Intent in = new Intent(AlphabetView.this, MainControl.class);
        startActivity(in);
    }
}