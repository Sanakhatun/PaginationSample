package com.professional.paginationsample.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.professional.paginationsample.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView tv_name, tv_qualification, tv_address;

    ImageView iv_image;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        init(itemView);

    }

    private void init(View itemView) {
        if (itemView != null) {
            tv_name = itemView.findViewById(R.id.tv_name);

            tv_qualification = itemView.findViewById(R.id.tv_qualification);

            tv_address = itemView.findViewById(R.id.tv_address);

            iv_image = itemView.findViewById(R.id.iv_image);
        }
    }

}
