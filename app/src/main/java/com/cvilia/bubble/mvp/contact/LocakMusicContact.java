package com.cvilia.bubble.mvp.contact;

import android.content.Context;

import com.cvilia.base.mvp.IPresenter;
import com.cvilia.base.mvp.IView;
import com.cvilia.bubble.bean.Music;

import java.util.List;

public class LocakMusicContact {
    public interface Presenter extends IPresenter<View> {
        void getLocalMisic(Context context);
    }

    public interface View extends IView {

        void showLocalMusic(List<Music> musics);

    }
}
