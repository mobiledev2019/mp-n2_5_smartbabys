package com.ptit.android.kidslearning.Music;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;
import com.ptit.android.kidslearning.R;

import java.util.List;

// hiển thị danh sách bài hát
public class MusicListFragment extends Fragment {
    DynamicListView lvMusic;
    MusicDataAccess access;
    ListMusic adapter;
    List<Music> listMusic;
    public mFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof mFragmentListener) {
            listener = (mFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement mFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_music, container, false);
        lvMusic = v.findViewById(R.id.music_list);
        dataToList();
        lvMusic.enableDragAndDrop();
        lvMusic.setDraggableManager(new TouchViewDraggableManager(R.id.list_row_draganddrop_touchview));
        lvMusic.setOnItemClickListener(itemClickListener);
        lvMusic.setOnItemLongClickListener(itemLongClickListener);
        lvMusic.setOnItemMovedListener(onItemMovedListener);
        return v;
    }

    private void dataToList() {
        access = new MusicDataAccess(getContext());
        listMusic = access.getMusicList();
        adapter = new ListMusic(getContext(), R.layout.item_music, listMusic);
        adapter.notifyDataSetChanged();
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lvMusic);
        lvMusic.setAdapter(animationAdapter);

    }

    AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            if (lvMusic != null) {
                lvMusic.startDragging(position - lvMusic.getHeaderViewsCount());
            }
            return true;
        }
    };

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            listener.onPositionSelected(position);
        }
    };

    OnItemMovedListener onItemMovedListener = new OnItemMovedListener() {
        @Override
        public void onItemMoved(int originalPosition, int newPosition) {
            adapter.swapItems(originalPosition, newPosition);
            adapter.notifyDataSetChanged();
        }
    };

    public interface mFragmentListener {
        void onPositionSelected(int position);
    }

}
