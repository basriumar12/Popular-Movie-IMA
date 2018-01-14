package com.blogbasbas.mymovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogbasbas.mymovie.network.CONSTANT;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.imgMovieDetail)
    ImageView imgMovieDetail;
    @BindView(R.id.tvJudulMovieDetail)
    TextView tvJudulMovieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent terimaData = getIntent();
        tvJudulMovieDetail.setText(terimaData.getStringExtra(CONSTANT.KEYNAMA));
        Picasso.with(DetailActivity.this)
                .load(CONSTANT.IMAGE_URL+terimaData.getStringExtra(CONSTANT.KEYGAMBAR))
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(50, 50)
                .centerCrop()
                .into(imgMovieDetail);

    }
}
