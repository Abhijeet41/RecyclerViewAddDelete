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

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.MyViewHolder> {

    ArrayList<DataModel> DataModelArrayList;
    ItemClickListener itemClickListener;
    Context context;

    public AddAdapter(MainActivity mainActivity, ArrayList<DataModel> checkBoxValue, ItemClickListener itemClickListener) {
        this.context = mainActivity;
        this.DataModelArrayList = checkBoxValue;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AddAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_add, parent, false);

        AddAdapter.MyViewHolder myViewHolder = new AddAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddAdapter.MyViewHolder holder, final int position) {
            final DataModel DataModel = DataModelArrayList.get(position);
            holder.txtname.setText(DataModel.getName());
            holder.img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnItemremove(position,0);
                }
            });
    }

    @Override
    public int getItemCount() {
        return DataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtname;
        ImageView img_close;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtname = (TextView) itemView.findViewById(R.id.txtname);
            this.img_close = (ImageView) itemView.findViewById(R.id.img_close);
        }
    }
}
