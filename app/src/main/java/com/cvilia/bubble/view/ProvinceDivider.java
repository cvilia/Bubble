package com.cvilia.bubble.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cvilia.base.util.DeviceUtil;
import com.cvilia.bubble.utils.DisplayUtil;

/**
 * author: lzy
 * date: 2020/9/28
 * describe：描述
 */
public class ProvinceDivider extends RecyclerView.ItemDecoration {

    private static final String TAG = ProvinceDivider.class.getSimpleName();

    private int horizontal;
    private Context context;

    public ProvinceDivider(Context context) {
        int totalViewWidth = DisplayUtil.dp2px(context, 320);
        horizontal = (DeviceUtil.getPxWidth(context) - totalViewWidth) / 6;
        this.context = context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = DisplayUtil.dp2px(context, 5);
        outRect.bottom = DisplayUtil.dp2px(context, 5);
        if (parent.getChildAdapterPosition(view) % 4 == 0) {
            outRect.right = 0;
            outRect.left = horizontal;
        } else if (parent.getChildAdapterPosition(view) % 4 == 1) {
            outRect.right = horizontal;
            outRect.left = horizontal;
        } else if (parent.getChildAdapterPosition(view) % 4 == 2) {
            outRect.right = horizontal;
            outRect.left = horizontal;
        } else if (parent.getChildAdapterPosition(view) % 4 == 3) {
            outRect.right = 0;
            outRect.left = horizontal;
        }
    }
}
