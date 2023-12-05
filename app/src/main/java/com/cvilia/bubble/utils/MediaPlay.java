package com.cvilia.bubble.utils;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MediaPlay {

    private static IjkMediaPlayer ijkPlayer;

    private static class Singleton {
        public static MediaPlay instance = new MediaPlay();
    }

    private MediaPlay() {
        initIjkPlayer();
    }

    public static MediaPlay getInstance() {
        return Singleton.instance;
    }

    private void initIjkPlayer() {
        ijkPlayer = new IjkMediaPlayer();

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

    public boolean isPlaying() {
        return ijkPlayer != null && ijkPlayer.isPlaying();
    }

    public void stop() {
        if (ijkPlayer != null && ijkPlayer.isPlaying()) {
            ijkPlayer.stop();
        }
    }

    public void pause(){
        if (ijkPlayer != null && ijkPlayer.isPlaying()) {
            ijkPlayer.pause();
        }
    }

    public void reset() {
        if (ijkPlayer != null) {
            ijkPlayer.reset();
        }
    }

}
