package com.ptit.android.kidslearning.Music;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.util.Swappable;
import com.ptit.android.kidslearning.R;

import java.util.Collections;
import java.util.List;

class ListMusic extends ArrayAdapter<Music> implements Swappable {
    private Context context;
    private List<Music> musicList;
    private int layoutId;
    private ImageView currentPlay;

    ListMusic(Context context, int layoutId, List<Music> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
        this.musicList = list;
    }

    @Override
    public Music getItem(int position) {
        return musicList.get(position);
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void swapItems(int positionOne, int positionTwo) {
        Collections.swap(this.musicList, positionOne, positionTwo);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        TextView tittle_music = convertView.findViewById(R.id.title_music);
        TextView tittle_category = convertView.findViewById(R.id.title_category);
        currentPlay = convertView.findViewById(R.id.imgCurrentPlay);
        Music music = musicList.get(position);
//        currentPlay.setVisibility(View.VISIBLE);
        tittle_music.setText(music.getName());
        tittle_category.setText(getNameCategory(music.getCategoryID()));
        return convertView;
    }

    private String getNameCategory(String code) {
        MusicDataAccess access = new MusicDataAccess(getContext());
        List<Category> categoryList = access.getListCategory();
        String name = "NONE";
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId().equals(code))
                name = categoryList.get(i).getName();
        }
        return name;
    }

}
