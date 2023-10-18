package com.cvilia.bubble.activity;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.bubble.R;
import com.cvilia.bubble.adapter.BubbleAdapter;
import com.cvilia.bubble.adapter.Day7Adapter;
import com.cvilia.bubble.adapter.Hour7Adapter;
import com.cvilia.bubble.base.BaseActivity;
import com.cvilia.bubble.bean.Music;
import com.cvilia.bubble.contact.BubbleContact;
import com.cvilia.bubble.databinding.ActivityBubbleBinding;
import com.cvilia.bubble.log.BubbleLogger;
import com.cvilia.bubble.presenter.BubblePresenter;
import com.cvilia.bubble.route.PageUrlConfig;
import com.cvilia.bubble.utils.MediaPlay;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class BubbleActivity extends BaseActivity<BubblePresenter> implements BubbleContact.View, View.OnClickListener {

    private static final String TAG = BubbleActivity.class.getSimpleName();

    private ActivityBubbleBinding mViewBinding;
    private List<Music> musics = new ArrayList<>();

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected View inflatRootView() {

        mViewBinding = ActivityBubbleBinding.inflate(getLayoutInflater());

        return mViewBinding.getRoot();
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mViewBinding.bubbleRv.setLayoutManager(layoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mViewBinding.bubbleRv);

        mViewBinding.playStatusIv.setOnClickListener(v -> {
            if (MediaPlay.getInstance(this).isPlaying()) {
                mViewBinding.playStatusIv.setImageResource(R.drawable.icon_playing);
                MediaPlay.getInstance(this).stop();
            } else {
                mViewBinding.playStatusIv.setImageResource(R.drawable.icon_pause);
                MediaPlay.getInstance(this).play(musics != null && musics.size() > 0 ? musics.get(0).getPath() : "");
            }
        });

    }

    @Override
    protected void getData() {
        mPresenter.getLocalMisic(this);
    }

    @Override
    protected BubblePresenter getPresenter() {
        return new BubblePresenter();
    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLocalMusic(List<Music> musics) {
        if (musics != null) {
            this.musics = musics;
            BubbleLogger.d(TAG, musics.toString());
            MediaPlay.getInstance(this).play(musics.get(0).getPath());
            BubbleAdapter adapter = new BubbleAdapter(musics);
            mViewBinding.bubbleRv.setAdapter(adapter);
            if (MediaPlay.getInstance(this).isPlaying()) {
                mViewBinding.playStatusIv.setImageResource(R.drawable.icon_playing);
            } else {
                mViewBinding.playStatusIv.setImageResource(R.drawable.icon_pause);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}