package com.example.ass2a;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoAdapter extends ArrayAdapter<Photo> {

    private final Context mContext;
    private final ArrayList<Photo> photoList;

    public PhotoAdapter(Context context, ArrayList<Photo> list) {
        super(context, 0, list);
        mContext = context;
        photoList = list;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_list_item_photo, parent, false);
        }

        Photo currentPhoto = photoList.get(position);

        TextView textView = listItem.findViewById(R.id.text_view);
        textView.setText(String.format("ID: %d\nTitle: %s\nAlbum ID: %d",
                currentPhoto.getId(), currentPhoto.getTitle(), currentPhoto.getAlbumId()));

        ImageView imageView = listItem.findViewById(R.id.image_view);
        Picasso.get().load(currentPhoto.getThumbnailUrl()).into(imageView);

        return listItem;
    }
}
