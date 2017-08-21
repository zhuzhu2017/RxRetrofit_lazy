package com.example.allen.rxretrofit_lazy.rxretrofit;

import com.example.allen.rxretrofit_lazy.entity.RetrofitEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * service统一接口数据
 * Created by allen on 2017/8/21.
 */

public interface ApiInterface {
    @POST("AppFiftyToneGraph/videoLink")
    Observable<RetrofitEntity> getAllVideoBy(@Body boolean once_no);
}
