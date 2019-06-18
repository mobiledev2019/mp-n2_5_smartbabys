package com.ptit.android.kidslearning.Music;

import android.content.Context;
import android.database.Cursor;

import com.ptit.android.kidslearning.DatabaseHandler.DatabseHandler;

import java.util.ArrayList;
import java.util.List;

//  xử lý phát âm thanh ,
public class MusicDataAccess {
    private List<Music> musicList;
    private Context context;
    private DatabseHandler handler;

    /**
     * Contructor
     */
    MusicDataAccess(Context context) {
        this.context = context;
        handler = new DatabseHandler(context);
    }

    /**
     * Get all music
     * Lấy tất cả bài hát
     */
    List<Music> getMusicList() {
        Music music;
        musicList = new ArrayList<>();
        Cursor cursor = handler.getCursor("SELECT * FROM tbl_song");
        if (cursor.moveToFirst()) {
            do {
                music = new Music(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                musicList.add(music);
            } while (cursor.moveToNext());
        }

        cursor.close();
        handler.closeDB();
        return musicList;
    }

    /**
     * Get category
     */
    List<Category> getListCategory() {
        Category category;
        List<Category> categories = new ArrayList<>();
        Cursor c = handler.getCursor("SELECT *FROM tbl_category");
        if (c.moveToFirst()) {
            do {
                category = new Category(c.getString(0), c.getString(1));
                categories.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categories;
    }
}
