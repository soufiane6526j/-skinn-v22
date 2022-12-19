package com.ayoub.wallpaper;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.ayoub.wallpaper.R;

public class MarginItemDecoration
        extends RecyclerView.ItemDecoration {

    private final int extraMargin;

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);

        // It's easy to put extra margin on the last item...


        // ...or you could give each item in the RecyclerView different
        // margins based on its position...
        if (position % 2 == 0) {
            outRect.right = extraMargin;
            outRect.bottom = extraMargin;
            outRect.top = extraMargin;
        } else {
            outRect.left = extraMargin;
            outRect.bottom = extraMargin;
            outRect.top = extraMargin;
        }

    }

    public MarginItemDecoration(Context context) {
        extraMargin = context.getResources()
                .getDimensionPixelOffset(R.dimen.extra_margin);
    }
}