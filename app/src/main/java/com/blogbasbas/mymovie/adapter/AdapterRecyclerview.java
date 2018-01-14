package com.blogbasbas.mymovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogbasbas.mymovie.R;

/**
 * Created by User on 14/01/2018.
 */

public class AdapterRecyclerview extends RecyclerView.Adapter<AdapterRecyclerview.MyHolder> {
    Context context;
    String namaFilm []={"Film 1","Film 2","Film 3","Film 4","Film 5","Film 6",};
    String Versi  []={"1","2","3","4","5","6",};
    LayoutInflater inflater;

    public AdapterRecyclerview(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public AdapterRecyclerview.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerview.MyHolder holder, int position) {

        holder.namafilm.setText(namaFilm[position]);
        holder.versi.setText(Versi[position]);
    }

    @Override
    public int getItemCount() {
        return namaFilm.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView namafilm, versi ;

        public MyHolder(View itemView) {
            super(itemView);
            namafilm = (TextView)itemView.findViewById(R.id.tvNamaFilm);
            versi = (TextView)itemView.findViewById(R.id.tvVersiFilm);

        }
    }
}
