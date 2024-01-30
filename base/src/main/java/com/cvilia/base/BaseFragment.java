package com.cvilia.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cvilia.base.mvp.BasePresenter;
import com.cvilia.base.mvp.IView;

import org.greenrobot.eventbus.EventBus;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * author: lzy
 * date: 2/19/21
 * describe：描述
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IView, CustomAdapt {

    private static final String TAG = BaseFragment.class.getSimpleName();

    protected T mPresenter;
    protected Context mContext;
    protected Activity mActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflateRootView(inflater,container);
        ARouter.getInstance().inject(this);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        if (registerEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getIntent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();

    }

    protected abstract void onCreate();
    protected abstract View inflateRootView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initView();

    protected abstract void getIntent();

    protected abstract void initData();

    public boolean registerEventBus() {
        return true;
    }

    protected abstract T getPresenter();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {

        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 360;
    }
}
