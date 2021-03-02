package com.cvilia.bubble.presenter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cvilia.bubble.base.BasePresenter;
import com.cvilia.bubble.bean.RandomImage;
import com.cvilia.bubble.config.Constants;
import com.cvilia.bubble.contact.MainContact;
import com.cvilia.bubble.net.Api;
import com.cvilia.bubble.net.OkUtil;
import com.cvilia.bubble.utils.MMKVUtil;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Objects;

import okhttp3.Response;


/**
 * author: lzy
 * date: 3/1/21
 * describe：MainActivity的Presenter
 */
public class MainPresenter extends BasePresenter<MainContact.View> implements MainContact.Presenter {
    @Override
    public void downloadLauncherPic(String launcherUrl) {
        if (!TextUtils.isEmpty(launcherUrl)) {
            String imgType = launcherUrl.split("\\.")[launcherUrl.split("\\.").length - 1];
            Glide.with((Activity) mView)
                    .asBitmap()
                    .load(launcherUrl)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            if (resource != null) {
                                saveImg2Local(resource, imgType);
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }
    }

    @Override
    public void requestLauncherUrl() {
        //请求随机图片
        HashMap<String, String> map = new HashMap<>();
        map.put("method", "mobile");
        map.put("format", "json");
        map.put("lx", "suiji");

        OkUtil.getInstance().get(Api.LAUNCHER_IMAGE, map, new OkUtil.MyCallback() {
            @Override
            public void success(Response res) throws IOException {
                Gson gson = new Gson();
                if (res != null) {
                    RandomImage randomImage = gson.fromJson(Objects.requireNonNull(res.body()).string(), RandomImage.class);
                    if (randomImage.getCode().equals("200")) {
                        MMKVUtil.saveString(Constants.LAUNCHER_IMAGE, randomImage.getImgurl());
                    }
                }
            }

            @Override
            public void failed(IOException e) {

            }
        });
    }

    private void saveImg2Local(Bitmap bitmap, String imgType) {
        String fileName = "bubble_launcher_pic_" + System.currentTimeMillis() + "." + imgType;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            contentValues.put(MediaStore.Images.Media.DESCRIPTION, fileName);
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            Uri uri = ((Activity) mView).getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            OutputStream outputStream = null;
            try {
                outputStream = ((Activity) mView).getContentResolver().openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/bubble");
            if (!storageDir.exists()) {
                storageDir.mkdir();
            }
            File file = new File(storageDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String path = file.getAbsolutePath();
            try {
                MediaStore.Images.Media.insertImage(((Activity) mView).getContentResolver(), path, fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            ((Activity) mView).sendBroadcast(intent);
            Toast.makeText(((Activity) mView), "图片保存成功", Toast.LENGTH_SHORT).show();
        }

    }


}
