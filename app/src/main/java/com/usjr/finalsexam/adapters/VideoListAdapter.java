package com.usjr.finalsexam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.usjr.finalsexam.R;
import com.usjr.finalsexam.entity.Video;

import java.util.List;

public class VideoListAdapter extends BaseAdapter {

    private Context     mContext;
    private List<Video> mVideos;

    public VideoListAdapter(Context context, List<Video> videos) {
        mContext = context;
        mVideos = videos;
    }

    @Override
    public int getCount() {
        return mVideos == null ? 0 : mVideos.size();
    }

    @Override
    public Video getItem(int position) {
        return mVideos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_video, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Video video = mVideos.get(position);
        if (video != null) {
            if (holder.imgThumbnail != null) {
                Glide.with(mContext).load(video.thumbnailUrl).into(holder.imgThumbnail);
            }
        }

        return convertView;
    }

    public void add(Video video) {
        if (video == null) {
            return;
        }

        mVideos.add(video);
        notifyDataSetChanged();
    }

    public class ViewHolder {
        ImageView imgThumbnail;
        TextView  tvTitle;

        public ViewHolder(View itemView) {
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            imgThumbnail= (ImageView) itemView.findViewById(R.id.imgThumbnail);
        }
    }
}
