package com.cvilia.bubbleweather.pages.selectcity;

import com.cvilia.bubbleweather.WeatherApplication;
import com.cvilia.bubbleweather.base.BasePresenter;
import com.cvilia.bubbleweather.bean.City;
import com.cvilia.bubbleweather.bean.User;
import com.cvilia.bubbleweather.sql.CityDao;
import com.cvilia.bubbleweather.sql.DaoSession;
import com.cvilia.bubbleweather.sql.UserDao;

import java.util.List;

/**
 * author: lzy
 * date: 2020/8/31
 * describe：选择城市Presenter
 */
public class SelectCityPresenter extends BasePresenter<SelectCityContact.View> implements SelectCityContact.Presenter {
    @Override
    public void readDb() {
        DaoSession daoSession = WeatherApplication.getDaoSessionInstance();
        CityDao dao = daoSession.getCityDao();
        UserDao userDao = daoSession.getUserDao();
        User user = new User(1l,"李振宇",27,"男");
        userDao.insert(user);
        List<City> cities = dao.loadAll();
        if (cities != null)
            mView.readDbSuccess(cities);
    }
}
