package com.cvilia.bubble.activity.center;

import androidx.annotation.NonNull;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.base.BaseActivity;
import com.cvilia.bubble.R;
import com.cvilia.bubble.mvp.contact.EatWahtContact;
import com.cvilia.bubble.databinding.ActivityEatWahtBinding;
import com.cvilia.bubble.mvp.presenter.EatWhatPresenter;
import com.cvilia.bubble.route.PageUrlConfig;
import com.cvilia.bubble.utils.MediaPlayerTools;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Route(path = PageUrlConfig.EAT_WHAT_PAGE)
public class EatWahtActivity extends BaseActivity<EatWhatPresenter> implements EatWahtContact.View, View.OnClickListener {

    private ActivityEatWahtBinding mBindings;
    private final Random mRandom = new Random();
    private final static int INTERVAL = 80;
    private Timer mTimer = new Timer();
    private final static int HANDLER_FLAG = 0x123;
    private boolean isSelecting = false;

    //控制图片旋转
    private boolean isPlaying = true;
    private Animation mAnimation;
    private final LinearInterpolator mInterpolator = new LinearInterpolator();
    //音乐播放
    private final MediaPlayer mPlayer = new MediaPlayer();

    private final static String[] mFoods = new String[]{"黄焖鸡", "红烧排骨", "土豆粉", "水饺", "肉夹馍", "酸辣粉", "牛肉拉面", "刀削面", "炸酱面", "烩面", "油泼面", "大盘鸡",
            "麻辣拌", "麻辣烫", "烤串", "小龙虾", "KFC", "金拱门", "凉皮", "披萨", "烤冷面", "煎饼果子", "牛排", "包子粥", "卤肉饭", "梅菜扣肉", "臭豆腐", "蛋包饭", "鸭脖", "火锅", "小碗菜",
            "大盘鸡", "沙县小吃", "便利蜂快餐", "酸菜鱼", "奶茶", "生煎", "重庆小面", "宜宾燃面", "热干面", "炒面", "煲仔饭", "泡面", "拌面", "馄饨", "盖浇饭", "烤鱼", "烤鸭", "羊蝎子", "大排骨", "烙饼", "小炒菜"};

    private final Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message message) {
            if (message.what == HANDLER_FLAG)
                mBindings.foodTv.setText(mFoods[mRandom.nextInt(mFoods.length)]);
            super.handleMessage(message);
        }
    };

    private TimerTask mTask = new TimerTask() {
        @Override
        public void run() {
            obtainMessage();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
        mBindings.selectBtn.setOnClickListener(this);
        mBindings.actionLayout.actionMore.setOnClickListener(this);
        mBindings.musicIv.setOnClickListener(this);

        mAnimation = AnimationUtils.loadAnimation(this, R.anim.music_rorarepeat);
        mBindings.musicIv.setImageResource(R.drawable.music_playing);
        mAnimation.setInterpolator(mInterpolator);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected View inflatRootView() {
        mBindings = ActivityEatWahtBinding.inflate(getLayoutInflater());
        return mBindings.getRoot();
    }


    @Override
    protected void getData() {

    }

    @Override
    protected EatWhatPresenter getPresenter() {
        return new EatWhatPresenter();
    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    private void resetTimer() {
        mTimer.cancel();
        mTimer = null;
        mTimer = new Timer();
        mTask.cancel();
        mTask = null;
        mTask = new TimerTask() {
            @Override
            public void run() {
                obtainMessage();
            }
        };
    }

    private void obtainMessage() {
        Message message = new Message();
        message.what = HANDLER_FLAG;
        mHandler.sendMessage(message);
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.selectBtn) {
            if (isSelecting) {
                isSelecting = false;
                resetTimer();
            } else {
                isSelecting = true;
                mTimer.scheduleAtFixedRate(mTask, 0, INTERVAL);
            }
        } else if (viewId == R.id.musicIv) {
            executeAnimation();
        }
    }

    private void executeAnimation() {
        if (isPlaying && MediaPlayerTools.isPlaying()) {
            isPlaying = false;
            mBindings.musicIv.setImageResource(R.drawable.music_pause);
            mBindings.musicIv.clearAnimation();
            MediaPlayerTools.pauseMusic();
        } else {
            isPlaying = true;
            mBindings.musicIv.setImageResource(R.drawable.music_playing);
            mBindings.musicIv.startAnimation(mAnimation);
            MediaPlayerTools.playMusic();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //进入应用后开始播放音乐并同时执行动画
        mBindings.musicIv.startAnimation(mAnimation);
        MediaPlayerTools.getInstance(this, R.raw.kindergarten);
        if (!MediaPlayerTools.isPlaying()) {
            MediaPlayerTools.playMusic();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBindings.musicIv.clearAnimation();
        if (MediaPlayerTools.isPlaying()) {
            MediaPlayerTools.pauseMusic();
        }
    }
}