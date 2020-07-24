package com.abhi41.recyclerviewadddelete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    private Context context;
    private ArrayList<DataModel> DataModels = new ArrayList<DataModel>();

    ItemClickListener itemClickListener;

    //make interface like this


    public CustomAdapter(MainActivity mainActivity, ArrayList<DataModel> data, ItemClickListener itemClickListener) {
        this.context = mainActivity;
        this.dataSet = data;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.MyViewHolder holder, final int position) {
        final DataModel model = dataSet.get(position);
        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;
        ImageView img_check = holder.img_check;

        textViewName.setText(model.getName());
        textViewVersion.setText(model.getVersion());
        imageView.setImageResource(model.getImage());

        if (model.getIsSelected() == 1)
        {
            img_check.setVisibility(View.VISIBLE);
        }else {
            img_check.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.isSelected == 0) {
                    itemClickListener.OnItemClick(position, 1);
                } else {
                    itemClickListener.OnItemClick(position,0);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon, img_check;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            this.img_check = (ImageView) itemView.findViewById(R.id.img_check);
        }
    }


}
