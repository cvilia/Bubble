package com.cvilia.bubble.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.cvilia.bubble.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * author: lzy
 * date: 2020/9/7
 * describe：图片相关工具类
 */
public class ImgUtils {

    /**
     * bitmap转drawable
     *
     * @param context
     * @param bitmap
     * @return
     */
    public static Drawable bmp2drawable(Context context, Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * drawable转bitmap
     *
     * @param context
     * @param drawableId
     * @return
     */
    public static Bitmap drawable2bmp(Context context, int drawableId) {
        return BitmapFactory.decodeResource(context.getResources(), drawableId);
    }

    /**
     * drawable转bitmap
     *
     * @param context
     * @param drawable
     * @return
     */
    public static Bitmap drawable2bmp(Context context, Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else {
            return null;
        }
        //点9图不再考虑
    }

    /**
     * 图片路径转bitmap
     *
     * @param context
     * @param imgPath
     * @return
     */
    public Bitmap path2bmp(Context context, String imgPath) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(imgPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取本地单张图片
     * 知乎图片选择器
     *
     * @param context
     * @param requestCode
     */
    public static void selectLocalImg(Activity context, int requestCode) {
        Matisse.from(context)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))
                .countable(true)
                .maxSelectable(1)
                .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.8f)
                .originalEnable(true)
                .maxOriginalSize(2)
                .imageEngine(new PicassoEngine())
                .forResult(requestCode);
    }


}
