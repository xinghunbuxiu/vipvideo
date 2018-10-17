package com.vipvideo.api;


import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lixh.rxhttp.ApiFactory;
import com.lixh.rxhttp.convert.JsonConverterFactory;

/**
 * Api Retrofit build
 */
public class Api {
    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);
    public ApiService apiService;

    //构造方法私有
    private Api(int hostType) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        apiService = ApiFactory.INSTANCE.createApi(ApiConstants.getHost(hostType), ApiService.class, JsonConverterFactory.create(gson));

    }

    public static ApiService getDefault() {
        return getDefault(1);
    }

    /**
     * @param hostType
     */
    public static ApiService getDefault(int hostType) {
        Api retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new Api(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.apiService;
    }

}