package com.cvilia.bubble.contact;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.cvilia.bubble.base.IPresenter;
import com.cvilia.bubble.base.IView;
import com.cvilia.bubble.bean.Day7WeatherBean;
import com.cvilia.bubble.bean.Music;

import java.util.List;

public class BubbleContact {
    public interface Presenter extends IPresenter<BubbleContact.View> {
        void getLocalMisic(Context context);
    }

    public interface View extends IView {

        void showLocalMusic(List<Music> musics);

    }
}
