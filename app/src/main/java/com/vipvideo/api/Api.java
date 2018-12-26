package com.vipvideo.api;


import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lixh.rxhttp.ApiFactory;
import com.lixh.rxhttp.convert.JsonConverterFactory;
import com.vipvideo.util.web.mahua.AesUtil;
import com.vipvideo.util.web.mahua.HeaderInfo;
import com.vipvideo.util.web.mahua.MhSdk;
import com.vipvideo.util.web.mahua.RHelp;

import okhttp3.Headers;
import okhttp3.HttpUrl;

import static com.vipvideo.api.BasicParamsInterceptor.Builder;

/**
 * Api Retrofit build
 */
public class Api {
    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);
    public ApiService apiService;

    //构造方法私有
    private Api(int hostType) {
        BasicParamsInterceptor paramsInterceptor = new Builder().addIntercept((url, header, param) -> {
            switch (url.host()){
                case "api.hbzjmf.com":
                    addHeaderMap(url,header);
                    break;
            }
        }).build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        apiService = ApiFactory.INSTANCE.createApi(ApiConstants.getHost(hostType), ApiService.class, JsonConverterFactory.create(gson), paramsInterceptor);

    }
    private void addHeaderMap(HttpUrl url, Headers.Builder header) {
        String encryptToHex = AesUtil.encryptToHex(MhSdk.init().getAppInfo().getEncryptPackageId(), AesUtil.getKey(true));
        String path = url.url().getPath();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(path);
        stringBuilder2.append(MhSdk.init().getAppInfo().getTerminal());
        stringBuilder2.append("/");
        stringBuilder2.append(RHelp.getPackageId());
        path = stringBuilder2.toString();

        HeaderInfo createHeaderInfo = MhSdk.init().getAppInfo().createHeaderInfo(path);

        header.add("X-Client-NonceStr", createHeaderInfo.getXClientNonceStr());
        header.add("X-Client-IP", createHeaderInfo.getXClientIP());
        header.add("X-Client-TimeStamp", createHeaderInfo.getXAuthTimeStamp());
        header.add("X-Client-Version", createHeaderInfo.getXClientVersion());
        header.add("X-Client-Sign", createHeaderInfo.getXClientSign());
        header.add("X-Auth-Token", createHeaderInfo.getXAuthToken());
        header.add("Accept", "application/json");
        header.add("accessToken", encryptToHex);
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