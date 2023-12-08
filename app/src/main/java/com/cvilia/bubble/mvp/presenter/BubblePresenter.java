package com.cvilia.bubble.mvp.presenter;

import static com.cvilia.bubble.mvp.contact.BubbleContact.*;

import android.content.Context;
import android.content.res.AssetManager;

import com.cvilia.base.mvp.BasePresenter;
import com.cvilia.bubble.model.BubbleModel;
import com.google.gson.Gson;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BubblePresenter extends BasePresenter<View> implements Presenter {
    @Override
    public void getAllComponent(Context context) {
        String json = getJsonFromAssets(context);
        if (json != null) {
            Gson gson = new Gson();
            BubbleModel bubble = gson.fromJson(json, BubbleModel.class);
            mView.showAllComponent(bubble);
        }
    }

    private String getJsonFromAssets(Context context) {
        String json = null;
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("component.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {

        }
        return json;
    }
}
