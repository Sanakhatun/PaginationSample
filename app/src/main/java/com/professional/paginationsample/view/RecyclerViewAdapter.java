package com.professional.paginationsample.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.professional.paginationsample.R;
import com.professional.paginationsample.repository.model.User;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final String TAG = RecyclerViewAdapter.class.getSimpleName();

    private Context context;

    private View view;

    private ArrayList<User.Data> arrayList;

    public RecyclerViewAdapter(Context context, ArrayList<User.Data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_custom_row, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        try {
            if (holder != null && arrayList.size() > 0) {

                User.Data data = arrayList.get(position);

                holder.tv_name.setText(data.first_name + " " + data.last_name);

                Glide.with(context).load(data.avatar).centerCrop().into(holder.iv_image);
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

}
