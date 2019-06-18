package com.ptit.android.kidslearning.Topic;

import android.content.Context;
import android.database.Cursor;

import com.ptit.android.kidslearning.DatabaseHandler.DatabseHandler;

import java.util.ArrayList;
import java.util.List;

public class TopicDataAccess {
    List<Topic> listTopic;
    Context context;
    DatabseHandler databseHandler;

    public TopicDataAccess(Context context) {
        this.context = context;
    }
    public List<Topic> getListTopic()
    {
        databseHandler = new DatabseHandler(context);
        Topic topic;
        listTopic = new ArrayList<>();
        Cursor cursor = databseHandler.getCursor("SELECT * FROM tbl_topic");

        if (cursor.moveToFirst()) {
            do
            {
                topic = new Topic(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                listTopic.add(topic);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listTopic;
    }
}
