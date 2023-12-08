package com.cvilia.bubble.mvp.presenter;

import static com.cvilia.bubble.mvp.contact.LocakMusicContact.*;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.cvilia.base.mvp.BasePresenter;
import com.cvilia.base.mvp.IView;
import com.cvilia.bubble.bean.Music;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicPresenter extends BasePresenter<View> implements Presenter, IView {
    @Override
    public void getLocalMisic(Context context) {
        Cursor cursor = null;
        try {
            List<Music> musics = new ArrayList<>();
            cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null);
            while (cursor != null && cursor.moveToNext()) {
                Music music = new Music();
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                music.setName(name);
                long time = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                music.setTime(time);
                String singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                music.setSinger(singer);
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));// 路径
                music.setPath(path);
                musics.add(music);
            }
            mView.showLocalMusic(musics);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }
}
