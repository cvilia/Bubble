package com.cvilia.base.http;

public class Api {

    private static final String SERVER = "https://www.mxnzp.com/api";

    /**
     * 获取所有新闻类型列表
     */
    public static final String ALL_NEWS_TYPES = SERVER + "/news/types/v2";

    /**
     * 获取单一类型新闻列表
     * <p>
     * typeId:新闻类型id
     * page:当前页码
     */
    public static final String GET_NEWS = SERVER + "/news/list/v2";
    public static final String GET_NEWS_DETAIL = SERVER + "/news/details/v2";


}
