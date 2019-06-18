package com.ptit.android.kidslearning.Topic;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ptit.android.kidslearning.R;
import com.ptit.android.kidslearning.Vocabulary.Vocabulary_HinhHoc;
import com.ptit.android.kidslearning.Vocabulary.Vocabulary_HoaQua;
import com.ptit.android.kidslearning.Vocabulary.Vocabulary_MauSac;
import com.ptit.android.kidslearning.Vocabulary.Vocabulary_ThoiTiet;
import com.ptit.android.kidslearning.Vocabulary.Vocabulary_ThucAn;

import java.util.List;

public class TopicView extends Activity {

    GridView gridView;
    List<Topic> listTopic;
    TopicDataAccess topicDataAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_menu);
        initeView();

    }

    public void initeView() {
        topicDataAccess = new TopicDataAccess(this);
        listTopic = topicDataAccess.getListTopic();

        Topic_Adapter topic_adapter = new Topic_Adapter(TopicView.this, listTopic);
        topic_adapter.notifyDataSetChanged();
        gridView.setAdapter(topic_adapter);
        gridView.setOnItemClickListener(onItemClickListener);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = null;
            ActivityOptionsCompat compat = null;
            switch (position) {
                case 0:
                    compat = ActivityOptionsCompat.makeSceneTransitionAnimation(TopicView.this, null);
                    intent = new Intent(TopicView.this, Vocabulary_HoaQua.class);
                    break;
                case 1:
                    compat = ActivityOptionsCompat.makeSceneTransitionAnimation(TopicView.this, null);
                    intent = new Intent(TopicView.this, Vocabulary_ThucAn.class);
                    break;
                case 2:
                    compat = ActivityOptionsCompat.makeSceneTransitionAnimation(TopicView.this, null);
                    intent = new Intent(TopicView.this, Vocabulary_MauSac.class);
                    break;
                case 3:
                    compat = ActivityOptionsCompat.makeSceneTransitionAnimation(TopicView.this, null);
                    intent = new Intent(TopicView.this, Vocabulary_ThoiTiet.class);
                    break;
                case 4:
                    compat = ActivityOptionsCompat.makeSceneTransitionAnimation(TopicView.this, null);
                    intent = new Intent(TopicView.this, Vocabulary_HinhHoc.class);
                    break;
            }
            startActivity(intent, compat.toBundle());
        }
    };
}

