package com.ptit.android.kidslearning.Alphabel;

import android.content.Context;
import android.database.Cursor;

import com.ptit.android.kidslearning.DatabaseHandler.DatabseHandler;

import java.util.ArrayList;
import java.util.List;

public class AlphatbetDataAccess {
    private List<Alphabet> listAlphabet;
    private Context context;
    private DatabseHandler databseHandler;

    public AlphatbetDataAccess(Context context) {
        this.context = context;
        databseHandler = new DatabseHandler(context);
    }

    List<Alphabet> getListAlphabet() {
        Alphabet alphabet;
        listAlphabet = new ArrayList<>();
        Cursor cursor = databseHandler.getCursor("SELECT * FROM tbl_alphabet");
        if (cursor.moveToFirst()) {
            do {
                alphabet = new Alphabet(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                listAlphabet.add(alphabet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listAlphabet;
    }
}
