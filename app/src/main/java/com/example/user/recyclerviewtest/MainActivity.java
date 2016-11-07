package com.example.user.recyclerviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    RecyclerView recyclerView;
    RecyclerView.Adapter Adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item(R.drawable.ap,"어피치"));
        items.add(new Item(R.drawable.dd,"오리"));
        items.add(new Item(R.drawable.jj,"제이지"));
        items.add(new Item(R.drawable.mj,"무지"));
        items.add(new Item(R.drawable.neo,"네오"));
        items.add(new Item(R.drawable.drem,"도라에몽"));
        items.add(new Item(R.drawable.angry,"앵그리버드"));
        items.add(new Item(R.drawable.pkc,"피카츄"));
        items.add(new Item(R.drawable.mk,"미키마우스"));

        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        //layoutManager = new LinearLayoutManager(this);
        //layoutManager = new GridLayoutManager(this,3);

       recyclerView.setLayoutManager(layoutManager);

        Adapter = new MyAdapter(items, mContext);
        recyclerView.setAdapter(Adapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private Context context;
        private ArrayList<Item> mItems;

        private int lastPosition = -1;

        public MyAdapter(ArrayList<Item> items, Context mContext){
            mItems = items;
            context = mContext;
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
            ViewHolder holder = new ViewHolder(v);
            Log.e("khlim","onCreateViewHolder");
            return holder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            Log.e("khlim","onBindViewHolder : "+position);
            holder.imageView.setImageResource(mItems.get(position).image);
            holder.textView.setText(mItems.get(position).imagetitle);
          //  setAnimation(holder.imageView, position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView;

            public ViewHolder(View view){
                super(view);
                imageView = (ImageView) view.findViewById(R.id.image);
                textView = (TextView) view.findViewById(R.id.imagetitle);
            }
        }

        private void setAnimation(View viewToAnimate, int position){
            if(position > lastPosition){
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }
    }
}
