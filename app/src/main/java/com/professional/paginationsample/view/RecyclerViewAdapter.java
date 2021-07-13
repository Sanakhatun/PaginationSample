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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = RecyclerViewAdapter.class.getSimpleName();

    private Context context;

    private View view;

    private ArrayList<User.Data> arrayList;

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_DATA = 1;
    private boolean isLoaderVisible = false;

    public RecyclerViewAdapter(Context context, ArrayList<User.Data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == TYPE_DATA) {
            return new RecyclerViewHolder(inflater.inflate(R.layout.layout_custom_row, parent, false));
        } else {
            return new ProgressHolder(inflater.inflate(R.layout.item_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {
            if (holder != null && holder instanceof RecyclerViewHolder && arrayList.size() > 0) {

                User.Data data = arrayList.get(position);

                ((RecyclerViewHolder) holder).tv_name.setText(data.first_name + " " + data.last_name);

                Glide.with(context).load(data.avatar).centerCrop().into(((RecyclerViewHolder) holder).iv_image);
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {

        if(!arrayList.get(position).first_name.equals("progress"))
        {
            return TYPE_DATA;
        }
        else
        {
            return TYPE_PROGRESS;
        }

    }

}
