package com.example.imb.championsleague.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public abstract class CursorRecycleViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {


    private Context mContext;
    private int mLastAnimatedItemPosition = -1;
    private Cursor mCursor;
    private boolean mDataValid;
    //    private int mRowIdColumn;
    private DataSetObserver mDataSetObserver;
    private OnItemClickListener onItemClickListener;


    public CursorRecycleViewAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mDataValid = cursor != null;
//        mRowIdColumn = mDataValid ? mCursor.getColumnIndex("id") : -1;
        mDataSetObserver = new NotifyingDataSetObserver();
        if (mCursor != null) {
            mCursor.registerDataSetObserver(mDataSetObserver);
        }
    }

    public Cursor getCursor() {
        return mCursor;
    }

    @Override
    public int getItemCount() {
        if (mDataValid && mCursor != null) {
            return mCursor.getCount();
        }
        return 0;
    }

//    @Override
//    public long getItemId(int position) {
//        if (mDataValid && mCursor != null && mCursor.moveToPosition(position)) {
//            return mCursor.getLong(mRowIdColumn);
//        }
//        return 0;
//    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    public abstract void onBindViewHolder(VH viewHolder, Cursor cursor);

    @Override
    public void onBindViewHolder(VH viewHolder, @SuppressLint("RecyclerView") final int position) {
        if (!mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }
        if (onItemClickListener != null)
            viewHolder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(view, mCursor));
        if (mLastAnimatedItemPosition < position) {
            animateItem(viewHolder.itemView);
            mLastAnimatedItemPosition = position;
        }
        onBindViewHolder(viewHolder, mCursor);
    }

    public void changeCursor(Cursor cursor) {
        Cursor old = swapCursor(cursor);
        if (old != null) {
            old.close();
        }
    }


    public Cursor swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return null;
        }
        final Cursor oldCursor = mCursor;
        if (oldCursor != null && mDataSetObserver != null) {
            oldCursor.unregisterDataSetObserver(mDataSetObserver);
        }
        mCursor = newCursor;
        if (mCursor != null) {
            if (mDataSetObserver != null) {
                mCursor.registerDataSetObserver(mDataSetObserver);
            }
//            mRowIdColumn = newCursor.getColumnIndex("id");
            mDataValid = true;
            notifyDataSetChanged();
        } else {
//            mRowIdColumn = -1;
            mDataValid = false;
            notifyDataSetChanged();
        }
        return oldCursor;
    }

    public Cursor swapCursorWithoutNotify(Cursor newCursor) {
        if (newCursor == mCursor) {
            return null;
        }
        final Cursor oldCursor = mCursor;
        if (oldCursor != null && mDataSetObserver != null) {
            oldCursor.unregisterDataSetObserver(mDataSetObserver);
        }
        mCursor = newCursor;
        if (mCursor != null) {
            if (mDataSetObserver != null) {
                mCursor.registerDataSetObserver(mDataSetObserver);
            }
//            mRowIdColumn = newCursor.getColumnIndex("id");
            mDataValid = true;
        } else {
//            mRowIdColumn = -1;
            mDataValid = false;
        }
        return oldCursor;
    }

//    @Override
//    public int getItemViewType(int position) {
//        mCursor.moveToPosition(position);
//        return getItemViewType(mCursor);
//    }
//
//    public int getItemViewType(Cursor cursor){return 0; }

    private void animateItem(View view) {
        view.setTranslationY(getScreenHeight((Activity) view.getContext()));
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(500)
                .start();
    }

    private int getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Cursor cursor);
    }

    private class NotifyingDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            mDataValid = true;
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            mDataValid = false;
            notifyDataSetChanged();
        }
    }
}
