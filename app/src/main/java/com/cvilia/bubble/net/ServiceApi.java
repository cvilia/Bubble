package com.cvilia.bubble.net;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Url;

/**
 * author: lzy
 * date: 3/1/21
 * describe：描述
 */
public interface ServiceApi {
    Observable<ResponseBody> downloadLauncherPic(@Url String imgUrl);
}
