package com.ptit.android.kidslearning.Vocabulary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.android.kidslearning.Alphabel.AlphabetView;
import com.ptit.android.kidslearning.MainControl;
import com.ptit.android.kidslearning.R;

import java.util.List;

public class Vocabulary_HinhHoc extends Activity {

    private int id_imgHinhHoc, id_sondHinhHoc;
    private int i = 0;
    ImageView imgHinhHoc;
    TextView txttvHinhHoc, txttaHinhHoc;
    Button btnnext, btnback;
    VocabularyDataAccess vocabularyDataAccess;
    List<Vocabulary> listVocabulary;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        initview();
        setView();
        StartAnimRight();
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listVocabulary.size() - 1 != i) {
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
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    i--;
                    setView();
                    StartAnimLeft();
                } else {
                    i = listVocabulary.size() - 1;
                    setView();
                    StartAnimLeft();
                }
            }
        });
    }

    public void initview() {
        imgHinhHoc = findViewById(R.id.imgv);
        txttvHinhHoc = findViewById(R.id.txttv);
        txttaHinhHoc = findViewById(R.id.txtta);
        btnnext = findViewById(R.id.btn_next);
        btnback = findViewById(R.id.btn_back);

        vocabularyDataAccess = new VocabularyDataAccess(this);
        listVocabulary = vocabularyDataAccess.getListHinhHoc();
    }

    public void setView() {
        id_imgHinhHoc = getResources().getIdentifier("com.ptit.android.kidslearning:drawable/" + listVocabulary.get(i).getImage_Word(), null, null);
        imgHinhHoc.setImageResource(id_imgHinhHoc);
        txttaHinhHoc.setText(listVocabulary.get(i).getTranlate());

        txttvHinhHoc.setText(listVocabulary.get(i).getKeyWord());

        imgHinhHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_sondHinhHoc = getResources().getIdentifier(listVocabulary.get(i).getSound_Word(), "raw", getPackageName());
                mediaPlayer = MediaPlayer.create(Vocabulary_HinhHoc.this, id_sondHinhHoc);
                mediaPlayer.start();
            }
        });
    }

    private void StartAnimRight() {
        Animation anim = AnimationUtils.loadAnimation(Vocabulary_HinhHoc.this, R.anim.slide_in_right);

        imgHinhHoc.setAnimation(anim);
        txttvHinhHoc.setAnimation(anim);
        txttaHinhHoc.setAnimation(anim);

        anim.start();
    }

    private void StartAnimLeft() {
        Animation anim = AnimationUtils.loadAnimation(Vocabulary_HinhHoc.this, R.anim.slide_in_left);

        imgHinhHoc.setAnimation(anim);
        txttvHinhHoc.setAnimation(anim);
        txttaHinhHoc.setAnimation(anim);

        anim.start();
    }

    public void BackHome(View view) {
        Intent in = new Intent(Vocabulary_HinhHoc.this, MainControl.class);
        startActivity(in);
    }
}