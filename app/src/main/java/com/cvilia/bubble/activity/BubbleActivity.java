package com.cvilia.bubble.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.bubble.R;
import com.cvilia.bubble.adapter.BubbleAdapter;
import com.cvilia.bubble.base.BaseActivity;
import com.cvilia.bubble.bean.Music;
import com.cvilia.bubble.contact.BubbleContact;
import com.cvilia.bubble.databinding.ActivityBubbleBinding;
import com.cvilia.bubble.log.BubbleLogger;
import com.cvilia.bubble.presenter.BubblePresenter;
import com.cvilia.bubble.route.PageUrlConfig;
import com.cvilia.bubble.utils.MediaPlay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class BubbleActivity extends BaseActivity<BubblePresenter> implements BubbleContact.View, View.OnClickListener {

    private static final String TAG = BubbleActivity.class.getSimpleName();

    private ActivityBubbleBinding mViewBinding;
    private List<Music> musics = new ArrayList<>();

    @Override
    protected void onViewCreated() {
        JSONObject json = new JSONObject();
        JSONObject config = new JSONObject();
        HashMap<String, Integer> map = new HashMap<>();
        try {
            json.put("Json-CVILIA", 14);
            System.out.println("Json--->" + new JSONObject(json.toString()));

            map.put("Map-CVILIW", 14);
            for (String key : map.keySet()) {
                config.put(key, map.get(key));
            }
            System.out.println("Map--->" + config);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
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
        showPlayStatus();
        mViewBinding.playStatusIv.setOnClickListener(v -> {
            if (MediaPlay.getInstance().isPlaying()) {
                MediaPlay.getInstance().pause();
                mViewBinding.playStatusIv.setImageResource(R.drawable.icon_pause);
            } else {
                MediaPlay.getInstance().reset();
                MediaPlay.getInstance().play(musics != null && musics.size() > 0 ? musics.get(0).getPath() : "");
                mViewBinding.playStatusIv.setImageResource(R.drawable.icon_playing);
            }
        });

    }

    private void showPlayStatus() {
        if (MediaPlay.getInstance().isPlaying()) {
            mViewBinding.playStatusIv.setImageResource(R.drawable.icon_playing);
        } else {
            mViewBinding.playStatusIv.setImageResource(R.drawable.icon_pause);
        }
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
            BubbleAdapter adapter = new BubbleAdapter(musics);
//            mViewBinding.bubbleRv.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {

    }
}