package com.ptit.android.kidslearning.Vocabulary;

import android.content.Context;
import android.database.Cursor;

import com.ptit.android.kidslearning.DatabaseHandler.DatabseHandler;

import java.util.ArrayList;
import java.util.List;

public class VocabularyDataAccess {
    private List<Vocabulary> listVocabulary;
    private DatabseHandler databseHandler;

    public VocabularyDataAccess(Context context) {
        databseHandler = new DatabseHandler(context);
    }

    /**
     * Get all list word
     */
    public List<Vocabulary> getListVocabulary() {
        List<Vocabulary> vocabularyList = new ArrayList<>();
        vocabularyList.addAll(getListHinhHoc());
        vocabularyList.addAll(getListHoaQua());
        vocabularyList.addAll(getListMauSac());
        vocabularyList.addAll(getListThoiTiet());
        vocabularyList.addAll(getListThucAn());
        return vocabularyList;
    }

    public List<Vocabulary> getListHoaQua() {
        Vocabulary vocabulary;
        listVocabulary = new ArrayList<>();
        Cursor cursor = databseHandler.getCursor("SELECT * FROM tbl_word WHERE TopicID = 'HoaQua'");

        if (cursor.moveToFirst()) {
            do {
                vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                listVocabulary.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listVocabulary;
    }

    public List<Vocabulary> getListHinhHoc() {
        Vocabulary vocabulary;
        listVocabulary = new ArrayList<>();
        Cursor cursor = databseHandler.getCursor("SELECT * FROM tbl_word WHERE TopicID = 'HinhHoc'");

        if (cursor.moveToFirst()) {
            do {
                vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                listVocabulary.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listVocabulary;
    }

    public List<Vocabulary> getListMauSac() {
        Vocabulary vocabulary;
        listVocabulary = new ArrayList<>();
        Cursor cursor = databseHandler.getCursor("SELECT * FROM tbl_word WHERE TopicID = 'MauSac'");

        if (cursor.moveToFirst()) {
            do {
                vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                listVocabulary.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listVocabulary;
    }

    public List<Vocabulary> getListThucAn() {
        Vocabulary vocabulary;
        listVocabulary = new ArrayList<>();
        Cursor cursor = databseHandler.getCursor("SELECT * FROM tbl_word WHERE TopicID = 'DoAn'");

        if (cursor.moveToFirst()) {
            do {
                vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                listVocabulary.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listVocabulary;
    }

    public List<Vocabulary> getListThoiTiet() {
        Vocabulary vocabulary;
        listVocabulary = new ArrayList<>();
        Cursor cursor = databseHandler.getCursor("SELECT * FROM tbl_word WHERE TopicID = 'ThoiTiet'");

        if (cursor.moveToFirst()) {
            do {
                vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                listVocabulary.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listVocabulary;
    }

}
