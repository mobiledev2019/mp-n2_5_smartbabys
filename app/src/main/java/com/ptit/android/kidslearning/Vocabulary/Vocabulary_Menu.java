package com.ptit.android.kidslearning.Vocabulary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ptit.android.kidslearning.R;

public class Vocabulary_Menu extends Activity {

    ImageView imgHinhHoc, imgHoaQua, imgThoiTiet, imgThucAn, imgMauSac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_menu);
        initview();
    }

    public void initview() {
        imgHinhHoc = findViewById(R.id.imgHinhHoc);
        imgHoaQua = findViewById(R.id.imgHoaQua);
        imgMauSac = findViewById(R.id.imgMauSac);
        imgThoiTiet = findViewById(R.id.imgThoiTiet);
        imgThucAn = findViewById(R.id.imgThucAn);

        imgThucAn.setOnClickListener(new Event());
        imgThoiTiet.setOnClickListener(new Event());
        imgMauSac.setOnClickListener(new Event());
        imgHoaQua.setOnClickListener(new Event());
        imgHinhHoc.setOnClickListener(new Event());
    }

    private class Event implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent in = null;
            switch (v.getId()) {
                case R.id.imgHinhHoc:
                    in = new Intent(Vocabulary_Menu.this, Vocabulary_HinhHoc.class);
                    break;
                case R.id.imgHoaQua:
                    in = new Intent(Vocabulary_Menu.this, Vocabulary_HoaQua.class);
                    break;
                case R.id.imgMauSac:
                    in = new Intent(Vocabulary_Menu.this, Vocabulary_MauSac.class);
                    break;
                case R.id.imgThoiTiet:
                    in = new Intent(Vocabulary_Menu.this, Vocabulary_ThoiTiet.class);
                    break;
                case R.id.imgThucAn:
                    in = new Intent(Vocabulary_Menu.this, Vocabulary_ThucAn.class);
                    break;
            }
            startActivity(in);
        }
    }
}
