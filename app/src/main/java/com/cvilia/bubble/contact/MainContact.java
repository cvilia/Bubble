package com.cvilia.bubble.contact;

import com.cvilia.bubble.base.IPresenter;
import com.cvilia.bubble.base.IView;

/**
 * author: lzy
 * date: 3/1/21
 * describe：MainActivity的Contact
 */
public class MainContact {
    public interface Presenter extends IPresenter<View> {
        void downloadLauncherPic(String url);

        void requestLauncherUrl();
    }

    public interface View extends IView {

    }
}
