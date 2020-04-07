package com.example.auktion.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.auktion.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.app_intro_1,
            R.drawable.app_intro_2
    };

    public String[] slide_headings = {
            "jual beli",
            "lelang"
    };

    public String[] slide_text = {
            "persetujuan saling mengikat antara penjual,\n" +
                    " yakni pihak yang menyerahkan barang,\n" +
                    " dan pembeli sebagai pihak yang membayar harga barang yang dijual",
            "proses membeli dan menjual barang atau jasa dengan cara menawarkan kepada penawar," +
                    " menawarkan tawaran harga lebih tinggi, dan kemudian menjual barang kepada" +
                    " penawar harga tertinggi."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView ivIntro = view.findViewById(R.id.iv_intro);
        TextView tvHead = view.findViewById(R.id.tv_head_intro);
        TextView tvText = view.findViewById(R.id.tv_sub_intro);

        ivIntro.setImageResource(slide_images[position]);
        tvHead.setText(slide_headings[position]);
        tvText.setText(slide_text[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
