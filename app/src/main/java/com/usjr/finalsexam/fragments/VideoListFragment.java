package com.usjr.finalsexam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usjr.finalsexam.R;
import com.usjr.finalsexam.adapters.VideoListAdapter;
import com.usjr.finalsexam.entity.Video;

import java.util.ArrayList;

public class VideoListFragment extends Fragment implements AdapterView.OnItemClickListener {

    public interface OnVideoSelectedListener {
        void videoSelectedListener(Video video);
    }

    private VideoListAdapter        mAdapter;
    private OnVideoSelectedListener mOnVideoSelectedListener;

    private DatabaseReference mRootDb;
    private DatabaseReference mVideosDb;
    ArrayList<String> mItemKeys = new ArrayList<>();


    public VideoListFragment() {
        // Required empty public constructor
    }

    public OnVideoSelectedListener getOnVideoSelectedListener() {
        return mOnVideoSelectedListener;
    }

    public VideoListFragment setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        mOnVideoSelectedListener = onVideoSelectedListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootDb = FirebaseDatabase.getInstance().getReference();
        mVideosDb = mRootDb.child("videos");
        mVideosDb.addChildEventListener(mChildEventListener);


    }

    private ChildEventListener mChildEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Video it = dataSnapshot.getValue(Video.class);
            mItemKeys.add(dataSnapshot.getKey());
            mAdapter.add(it);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String Keychanged = dataSnapshot.getKey();
            int IndexChanged = mItemKeys.indexOf(Keychanged);
            Video i = dataSnapshot.getValue(Video.class);
           // mAdapter.set(IndexChanged, i);
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String KeyRemoved = dataSnapshot.getKey();
            int IndexRemoved = mItemKeys.indexOf(KeyRemoved);
            Log.d("chan", String.valueOf(IndexRemoved));
            mItemKeys.remove(KeyRemoved);
           // mAdapter.remove(IndexRemoved);

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        mAdapter = new VideoListAdapter(getContext(), new ArrayList<Video>());
        listView.setAdapter(mAdapter);




        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnVideoSelectedListener == null) {
            return;
        }




    }
}
