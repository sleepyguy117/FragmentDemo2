package com.example.hw0935.fragmentdemo2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    RecyclerView r;
    RecyclerView.LayoutManager lm;

    MyAdapter adapter;
    ItemTouchHelper itemTouchHelper;

    List items = new ArrayList<String>(Arrays.asList("one", "two", "three", "four", "five", "sixz", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "hans", "wee", "meh"));

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            adapter.moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            int position = viewHolder.getAdapterPosition();

            adapter.getValues().remove(position);

            adapter.notifyDataSetChanged();
        }

        @Override
        public boolean isLongPressDragEnabled() {

            boolean x = super.isLongPressDragEnabled();;

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        r = findViewById(R.id.recycler);

        //lm = new GridLayoutManager(this, 3);

        //lm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        itemTouchHelper = new ItemTouchHelper(simpleCallback);

        r.setLayoutManager(lm);

        MyAdapter m = new MyAdapter();

        m.setItemTouchHelper(itemTouchHelper);
//        String[] args = new String[]{"firstarg", "secondarg", "thirdarg"};
//
//        List<String> stringList = new ArrayList<String>();
//        stringList.addAll(Arrays.asList(args));

        m.setValues(items);
        adapter = m;

        r.setAdapter(m);

        itemTouchHelper.attachToRecyclerView(r);
    }
}
