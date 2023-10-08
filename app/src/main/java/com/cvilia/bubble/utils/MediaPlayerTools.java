package com.cvilia.bubble.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * created by: cvilia
 * e-mail: cvilia@163.com
 * date: 2022-10-18-22:50
 * describe:
 */
public class MediaPlayerTools {

    private static MediaPlayer mPlayer;

    private MediaPlayerTools() {
    }

    public static MediaPlayerTools getInstance(Context context, int resId) {
        initPlayer(context, resId);
        return SingleInstance.instance;
    }

    private static void initPlayer(Context context, int resId) {
        mPlayer = MediaPlayer.create(context, resId);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    private static class SingleInstance {
        @SuppressLint("StaticFieldLeak")
        private static final MediaPlayerTools instance = new MediaPlayerTools();
    }

    public static void playMusic() {
        mPlayer.start();
    }

    public static void pauseMusic() {
        mPlayer.pause();
    }

    public static boolean isPlaying() {
        return mPlayer.isPlaying();
    }

}
