package com.cvilia.bubble.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.cvilia.base.BaseActivity;
import com.cvilia.bubble.R;
import com.cvilia.bubble.adapter.LocalMusicAdapter;
import com.cvilia.bubble.bean.Music;
import com.cvilia.bubble.mvp.contact.LocakMusicContact;
import com.cvilia.bubble.databinding.ActivityLocalMusicBinding;
import com.cvilia.bubble.log.BubbleLogger;
import com.cvilia.bubble.mvp.presenter.LocalMusicPresenter;
import com.cvilia.bubble.utils.MediaPlay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LocalMusicActivity extends BaseActivity<LocalMusicPresenter> implements LocakMusicContact.View, View.OnClickListener {

    private static final String TAG = LocalMusicActivity.class.getSimpleName();

    private ActivityLocalMusicBinding mViewBinding;
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

        mViewBinding = ActivityLocalMusicBinding.inflate(getLayoutInflater());

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
    protected LocalMusicPresenter getPresenter() {
        return new LocalMusicPresenter();
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
            LocalMusicAdapter adapter = new LocalMusicAdapter(musics);
//            mViewBinding.bubbleRv.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {

    }
}