package com.cvilia.bubbleweather.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.config.Constants;
import com.tencent.mmkv.MMKV;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * author: lzy
 * date: 2020/9/10
 * describe：复制db数据库到本地
 */
public class CopyDb2Local {

    public static void copy2localdb(Context context) {

        PackageInfo info = DeviceUtil.getAppInfo(context);
        String localPath = info != null && info.applicationInfo != null ? info.applicationInfo.dataDir : "";
        String dataPath = localPath + File.separator + "databases/";
        File file = new File(dataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File dbFile = new File(dataPath + "weatherDb.db");
        InputStream is = context.getResources().openRawResource(R.raw.major);
        if (!dbFile.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(dbFile);
                byte[] bs = new byte[1024];
                int len;
                while ((len = is.read(bs)) != -1) {
                    fos.write(bs, 0, len);
                }
                fos.flush();
                fos.close();
                is.close();
                MMKV.defaultMMKV().encode(Constants.COPY_DB_ALREADY, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
