package com.yuqirong.lib;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

/**
 * @author maomao
 * @email qirong.yu@jubaobar.com
 * @date 2017-3-2
 */

public class CardItemTouchHelperCallback<T> extends ItemTouchHelper.Callback {

    private final RecyclerView.Adapter adapter;
    private List<T> dataList;
    private OnSwipeListener<T> mListener;


    public CardItemTouchHelperCallback(RecyclerView.Adapter adapter, List<T> dataList) {
        this.adapter = adapter;
        this.dataList = dataList;
    }

    public CardItemTouchHelperCallback(RecyclerView.Adapter adapter, List<T> dataList, OnSwipeListener<T> listener) {
        this.adapter = adapter;
        this.dataList = dataList;
        this.mListener = listener;
    }

    public void setOnSwipedListener(OnSwipeListener<T> mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof CardLayoutManager) {
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int layoutPosition = viewHolder.getLayoutPosition();
        T remove = dataList.remove(layoutPosition);
        adapter.notifyDataSetChanged();
        if (mListener != null) {
            mListener.onSwiped(remove, direction == ItemTouchHelper.LEFT ? CardConfig.SWIPED_LEFT : CardConfig.SWIPED_RIGHT);
        }
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float ratio = dX / getThreshold(recyclerView, viewHolder);
            // ratio 最大为 1 或 -1
            if (ratio > 1) {
                ratio = 1;
            } else if (ratio < -1) {
                ratio = -1;
            }
            viewHolder.itemView.setRotation(ratio * CardConfig.DEFAULT_ROTATE_DEGREE);
            int childCount = recyclerView.getChildCount();
            // 当数据源个数大于最大显示数时
            if (childCount > CardConfig.DEFAULT_SHOW_ITEM) {
                for (int position = 1; position < childCount - 1; position++) {
                    int index = childCount - position - 1;
                    View view = recyclerView.getChildAt(position);
                    view.setScaleX(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setTranslationY((index - Math.abs(ratio)) * 75);
                }
            } else {
                // 当数据源个数小于或等于最大显示数时
                for (int position = 0; position < childCount - 1; position++) {
                    int index = childCount - position - 1;
                    View view = recyclerView.getChildAt(position);
                    view.setScaleX(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setTranslationY((index - Math.abs(ratio)) * 75);
                }
            }
            if (mListener != null) {
                if (ratio != 0) {
                    mListener.onSwiping(viewHolder, ratio, ratio < 0 ? CardConfig.SWIPING_LEFT : CardConfig.SWIPING_RIGHT);
                } else {
                    mListener.onSwiping(viewHolder, ratio, CardConfig.SWIPING_NONE);
                }
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setRotation(0f);
    }

    private float getThreshold(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return recyclerView.getWidth() * getSwipeThreshold(viewHolder);
    }

}
