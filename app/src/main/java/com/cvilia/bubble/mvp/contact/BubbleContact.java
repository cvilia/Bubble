package com.cvilia.bubble.mvp.contact;

import android.content.Context;

import com.cvilia.base.mvp.IPresenter;
import com.cvilia.base.mvp.IView;
import com.cvilia.bubble.bean.Music;
import com.cvilia.bubble.model.BubbleModel;

import java.util.List;

public class BubbleContact {
    public interface Presenter extends IPresenter<View> {
        void getAllComponent(Context context);
    }

    public interface View extends IView {

        void showAllComponent(BubbleModel bubble);

    }
}
