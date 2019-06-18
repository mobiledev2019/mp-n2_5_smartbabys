package com.ptit.android.kidslearning.Vocabulary;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ptit.android.kidslearning.PrefManager;
import com.ptit.android.kidslearning.R;
import com.ptit.android.kidslearning.Vocabulary.Vocabulary_Menu;

public class DialogNotificationTime extends Activity {

    private LinearLayout btnPlayNow;
    private ImageView dialogBackground;
    private ImageView imgClose;
    private TextView txtDialogContentVietNam;
    private TextView txtDialogContent;
    private TextView txtPlayNow;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_notificaion);

//        prefManager = new PrefManager(this);
//
//        dialogBackground = findViewById(R.id.dialog_background);
//        txtDialogContentVietNam = findViewById(R.id.txt_dialog_content_vietnam);
//        txtDialogContent = findViewById(R.id.txt_dialog_content);
//        txtPlayNow = findViewById(R.id.txt_play_now);
//        btnPlayNow = findViewById(R.id.btn_play_now);
//        imgClose = findViewById(R.id.img_close);
//
//        txtDialogContentVietNam.setText(prefManager.getSettingSleepingTime());
//        txtDialogContent.setText(R.string.);
//        txtPlayNow.setText("Let's go");
//        Glide.with(this)
//                .load(R.drawable.bg_dialog_sleep)
//                .into(dialogBackground);
//
//
//        btnPlayNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Remove the notification from notification tray
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.cancel(NotificationSleepingTimeService.NOTIFICATION_ID);
//                Intent intent = new Intent(DialogNotificationSleepingTime.this, Vocabulary_Menu.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        imgClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Remove the notification from notification tray
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.cancel(NotificationSleepingTimeService.NOTIFICATION_ID);
//                finish();
//            }
//        });
    }
}
