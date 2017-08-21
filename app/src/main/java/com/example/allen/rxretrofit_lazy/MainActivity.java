package com.example.allen.rxretrofit_lazy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.allen.rxretrofit_lazy.entity.RetrofitEntity;
import com.example.allen.rxretrofit_lazy.rxretrofit.ApiInterface;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_get_data)
    Button btnGetData;
    @BindView(R.id.tv_show_data)
    TextView tvShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get_data)
    public void onViewClicked() {
        //请求数据
        getData();
    }

    /**
     * 请求数据
     */
    private void getData() {
        //手动创建一个okhttpClient对象，并设置超时时间
        okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://www.izaodao.com/Api/")
                .build();
        //创建加载框
        final ProgressDialog dialog = new ProgressDialog(this);
        //请求数据
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Observable<RetrofitEntity> observable = apiInterface.getAllVideoBy(true);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RetrofitEntity>() {
                    @Override
                    public void onCompleted() {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onNext(RetrofitEntity retrofitEntity) {
                        tvShowData.setText("无封装：\n" + retrofitEntity.getData().toString());
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        dialog.show();
                    }
                });
    }
}
