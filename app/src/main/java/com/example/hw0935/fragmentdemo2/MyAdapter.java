package com.example.hw0935.fragmentdemo2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<String> values;
    ItemTouchHelper itemTouchHelper;

    public ItemTouchHelper getItemTouchHelper() {
        return itemTouchHelper;
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView firstLine;
        public TextView secondLine;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            firstLine = (TextView) v.findViewById(R.id.firstLine);
            secondLine = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void moveItem(int oldPos, int newPos) {
        String item = values.get(oldPos);
        values.remove(oldPos);
        values.add(newPos, item);
        notifyItemMoved(oldPos, newPos);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        viewHolder.firstLine.setText(values.get(i).toLowerCase());
        viewHolder.secondLine.setText(values.get(i).toUpperCase());

        if(i % 2 == 1)
            viewHolder.layout.setBackgroundColor(viewHolder.layout.getResources().getColor(android.R.color.holo_red_light));

        //use it as a drag handle
        viewHolder.secondLine.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    itemTouchHelper.startDrag(viewHolder);
                }

                return false;
            }
        });

        //use it to put it in edit mode
//        viewHolder.secondLine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                itemTouchHelper.startDrag(viewHolder);
//            }
//        });
       // if(i % 3 == 1)
            //viewHolder.secondLine.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
