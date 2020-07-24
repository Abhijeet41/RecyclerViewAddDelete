package com.abhi41.recyclerviewadddelete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    private CustomAdapter adapter;
    private AddAdapter addAdapter;


    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView, add_recycler_view;
    private static ArrayList<DataModel> data = new ArrayList<>();
    // static View.OnClickListener myOnClickListener;
    private static ArrayList<DataModel> addModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        add_recycler_view = (RecyclerView) findViewById(R.id.add_recycler_view);


        recyclerView.setHasFixedSize(true);
        add_recycler_view.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.versionArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }
        adapter = new CustomAdapter(this, data, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        addAdapter = new AddAdapter(this, addModelArrayList, this);
        add_recycler_view.setAdapter(addAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        add_recycler_view.setLayoutManager(manager);
        addAdapter.notifyDataSetChanged();



    }


    @Override
    public void OnItemClick(int position, int isSelected)
    {
        DataModel dataModel = data.get(position);

        if (isSelected == 1)
        {
            dataModel.setIsSelected(1);

            addModelArrayList.add(0, dataModel);

            if (addModelArrayList.isEmpty())
            {
                add_recycler_view.setVisibility(View.GONE);
            }else {
                add_recycler_view.setVisibility(View.VISIBLE);
            }

        }
        else
        {
            dataModel.setIsSelected(0);

            for (int i = 0; i < addModelArrayList.size(); i++)
            {
                if (addModelArrayList.get(i).getName().equals(dataModel.getName()))
                {
                    addModelArrayList.remove(i);
                }
            }
            if (addModelArrayList.isEmpty())
            {
                add_recycler_view.setVisibility(View.GONE);
            }else {
                add_recycler_view.setVisibility(View.VISIBLE);
            }
        }

        adapter.notifyDataSetChanged();
        addAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemremove(int position, int isSelected) {
        DataModel dataModel = addModelArrayList.get(position);


        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getName().equals(dataModel.getName())) {
                data.get(i).setIsSelected(0);
            }
        }

        for (int i = 0; i < addModelArrayList.size(); i++) {
            if (addModelArrayList.get(i).getName().equals(dataModel.getName())) {
                addModelArrayList.remove(i);
            }
        }

        adapter.notifyDataSetChanged();
        addAdapter.notifyDataSetChanged();
    }
}