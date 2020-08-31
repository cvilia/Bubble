package com.cvilia.bubbleweather.pages.selectcity;

import android.content.Context;
import android.util.Log;

import com.cvilia.bubbleweather.base.BasePresenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * author: lzy
 * date: 2020/8/31
 * describe：选择城市Presenter
 */
public class SelectCityPresenter extends BasePresenter<SelectCityContact.View> implements SelectCityContact.Presenter {

    @Override
    public void readJson(Context context) {
        readJsonFile(new File("/Users/lizhenyu/Desktop/personal/BubbleWeather/app/src/main/assets/cities.txt"));
    }

    public String readJsonFile(File jsonFile) {
        String jsonStr = "";
        try {
            //File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
