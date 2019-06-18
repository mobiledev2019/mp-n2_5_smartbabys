package com.ptit.android.kidslearning.Topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.android.kidslearning.R;

import java.util.List;

public class Topic_Adapter extends BaseAdapter {

    List<Topic> list;
    Context context;
    public Topic_Adapter(Context context, List<Topic> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler grid = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            grid = new ViewHodler();
            convertView = inflater.inflate(R.layout.topicadapter,null);
            grid.imgTopic = convertView.findViewById(R.id.imgTopic);
            grid.txtTopic = convertView.findViewById(R.id.txtTopic);
            convertView.setTag(grid);
        }
        else {
            grid = (ViewHodler) convertView.getTag();
        }
        int id_imgAlphabet = context.getResources().getIdentifier("com.ptit.android.kidslearning:drawable/" + list.get(position).getImage(), null, null);
        grid.imgTopic.setImageResource(id_imgAlphabet);
        grid.txtTopic.setText(list.get(position).getName());
        return convertView;
    }
    public class ViewHodler{
        ImageView imgTopic;
        TextView txtTopic;
    }
}
