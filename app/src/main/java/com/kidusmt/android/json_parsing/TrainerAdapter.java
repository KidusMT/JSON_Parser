package com.kidusmt.android.json_parsing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by KidusMT on 8/9/2017.
 */

public class TrainerAdapter extends ArrayAdapter<Trainer> {
    ArrayList<Trainer> trainersList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public TrainerAdapter(Context context, int resource, ArrayList<Trainer> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        trainersList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.tvDescription = (TextView) v.findViewById(R.id.tvDescriptionn);
            holder.tvCourse = (TextView) v.findViewById(R.id.tvCourse);
            holder.tvCountry = (TextView) v.findViewById(R.id.tvCountry);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.ic_amadou_daffe);
        Log.e("====>",trainersList.get(position).getImage());
        Glide.with(getContext()).load(trainersList.get(position).getImage())
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(130,130)
                    .crossFade()
                    .into(holder.imageview);
        holder.tvName.setText(trainersList.get(position).getName());
        holder.tvDescription.setText(trainersList.get(position).getDescription());
        holder.tvCountry.setText(trainersList.get(position).getCountry());
        holder.tvCourse.setText("Course: " + trainersList.get(position).getProfessional_course());
        holder.tvCountry.setText("Country: " + trainersList.get(position).getCountry());
        return v;

    }

    static class ViewHolder{
        public ImageView imageview;
        public TextView tvName;
        public TextView tvDescription;
        public TextView tvCourse;
        public TextView tvCountry;
    }
//    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap>{
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            Glide.with(getContext()).load(urldisplay)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .into(bmImage);
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
////            Glide.with(getContext()).load(current.getThumbnail())
////                    .thumbnail(0.5f)
////                    .crossFade()
////                    .into(bmImage);
//            //bmImage.setImageBitmap(result);
//        }
//    }
}
