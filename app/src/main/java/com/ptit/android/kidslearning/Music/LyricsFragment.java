package com.ptit.android.kidslearning.Music;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ptit.android.kidslearning.R;
import com.ptit.android.kidslearning.Utils.BusStation;
import com.ptit.android.kidslearning.Utils.Messaage;
import com.squareup.otto.Subscribe;

import java.util.List;

// hiển thị lời bài hát
public class LyricsFragment extends Fragment {
    TextView tvName, tvLyrics;
    int data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lyrics_view, container, false);
        tvName = v.findViewById(R.id.name_music);
        tvLyrics = v.findViewById(R.id.tvLyrics);
        setLyrics(data);
        return v;
    }

    void setLyrics(int value) {
        MusicDataAccess accessLyrics = new MusicDataAccess(getContext());
        List<Music> listLyrics = accessLyrics.getMusicList();
        String name = listLyrics.get(value).getName();
        String lyrics = listLyrics.get(value).getLyrics();
        tvName.setText(name);
        tvLyrics.setText(lyrics);
    }

    @Subscribe
    public void receiveMessage(Messaage msg) {
        setLyrics(msg.getValue());
    }

    @Override
    public void onResume() {
        super.onResume();
        BusStation.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusStation.getBus().unregister(this);
    }
}

