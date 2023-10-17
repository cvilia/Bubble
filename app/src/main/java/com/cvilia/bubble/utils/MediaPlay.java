package com.cvilia.bubble.utils;

import android.content.Context;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MediaPlay {

    private static IjkMediaPlayer ijkPlayer;
    private static Context mContext;

    private static class Singleton {
        public static MediaPlay instance = new MediaPlay();
    }

    private MediaPlay() {
        initIjkPlayer();
    }

    public static MediaPlay getInstance(Context context) {
        mContext = context;
        return Singleton.instance;
    }

    private void initIjkPlayer() {
        ijkPlayer = new IjkMediaPlayer();
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);

    }


    public void play(String url) {
        try {
            ijkPlayer.setDataSource(url);
            ijkPlayer.prepareAsync();
            ijkPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
