package com.yuqirong.cardslideview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yuqirong.lib.CardConfig;
import com.yuqirong.lib.CardItemTouchHelperCallback;
import com.yuqirong.lib.CardLayoutManager;
import com.yuqirong.lib.OnSwipeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Button button = (Button) findViewById(R.id.btn);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Log.i(TAG, "onCreateViewHolder");
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                Log.i(TAG, "position = " + position);
                ((MyViewHolder) holder).textView.setText(list.get(position));
            }

            @Override
            public int getItemCount() {
                return list.size();
            }

            class MyViewHolder extends RecyclerView.ViewHolder {

                TextView textView;

                MyViewHolder(View itemView) {
                    super(itemView);
                    textView = (TextView) itemView.findViewById(R.id.tv);
                }

            }
        });
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
        cardCallback.setOnSwipedListener(new OnSwipeListener<String>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

            }

            @Override
            public void onSwiped(String o, int direction) {
                Toast.makeText(MainActivity.this, direction == CardConfig.SWIPED_LEFT ? "left" : "right", Toast.LENGTH_SHORT).show();
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }

}
