package com.ptit.android.kidslearning.DatabaseHandler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabseHandler extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase db;
    private static final String dbPath = "/data/data/com.ptit.android.kidslearning/databases/";
    private static final String dbName = "db.sqlite";
    public static final String path = dbPath + dbName;
    public static final int version = 1;

    public DatabseHandler(Context context) {
        super(context, dbName, null, version);
        this.context = context;
     //   openDB();
    }

    /**
     * Check and open database
     */
    private SQLiteDatabase openDB() {
        SQLiteDatabase check = null;
        try {
            check = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (check == null) {
            copyDB();
        } else {
            check.close();
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        }
        return db;
    }

    /**
     * Close Database
     */
    public void closeDB() {
        db.close();
    }

    /**
     * Copy Database
     */
    private boolean copyDB() {
        try {
            this.getReadableDatabase();
            InputStream mInput = context.getAssets().open(getDatabaseName());
            OutputStream mOutput = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = mInput.read(buffer)) > 0) {
                mOutput.write(buffer, 0, length);
            }
            mOutput.flush();
            mOutput.close();
            mInput.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    //Get cursor
    public Cursor getCursor(String sql) {
        openDB();
        return db.rawQuery(sql, null);
    }

    //Get record count
    public int getCount(String sql) {
        openDB();
        Cursor cursor = getCursor(sql);
        int count = cursor.getCount();
        closeDB();
        return count;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

