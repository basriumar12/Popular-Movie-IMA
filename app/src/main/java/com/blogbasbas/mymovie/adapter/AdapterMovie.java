package com.blogbasbas.mymovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogbasbas.mymovie.DetailActivity;
import com.blogbasbas.mymovie.R;
import com.blogbasbas.mymovie.network.CONSTANT;
import com.blogbasbas.mymovie.pojo.ResultsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 14/01/2018.
 */

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MyHolder> {

    Context context;
    List<ResultsItem> dataMovie;
    LayoutInflater inflater;

    public AdapterMovie(Context context, List<ResultsItem> dataMovie) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.dataMovie = dataMovie;
    }
    @Override
    public AdapterMovie.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new MyHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterMovie.MyHolder holder, int position) {
        final ResultsItem item =dataMovie.get(position);
        holder.tvTitle.setText(item.getOriginalTitle());
        Log.d(TAG, "onBindViewHolder: "+item.getOriginalTitle());
        Picasso.with(context)
                .load(CONSTANT.IMAGE_URL+item.getPosterPath())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .resize(50, 50)
                .centerCrop()
                .into(holder.imgMovie);
       //event klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kirimData = new Intent(context, DetailActivity.class);
                kirimData.putExtra(CONSTANT.KEYNAMA, item.getOriginalTitle());
                kirimData.putExtra(CONSTANT.KEYGAMBAR, item.getPosterPath());
                context.startActivity(kirimData);

            }
        });
    }
    @Override
    public int getItemCount() {
        return dataMovie.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
     ImageView imgMovie;
      TextView tvTitle ;
        public MyHolder(View itemView) {
             super(itemView);

         imgMovie = (ImageView)itemView.findViewById(R.id.imgMovie);
         tvTitle = (TextView)itemView.findViewById(R.id.tvJudulMovie);

        }
    }
}
