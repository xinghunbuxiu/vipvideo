package com.vipvideo.presenter;

import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.vipvideo.api.Api;
import com.vipvideo.crypto.EthereumAddressCreator;
import com.vipvideo.bean.TaskListData;
import com.vipvideo.crypto.NumericUtil;
import com.vipvideo.ui.fragment.HomeFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;


/**
 * Created by LIXH on 2016/11/14.
 * email lixhVip9@163.com
 * des
 */
public class HomePresenter extends BasePresenter {
    HomeFragment homeFragment;
    private static final String HEX_PREFIX = "0x";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        homeFragment = getFragment();
    }

    public void getTxsInfo(final List<TaskListData> taskListData, int p) {
        rxHelper.createSubscriber(Api.getDefault().getTxsInfo(p), new RxSubscriber<String>(homeFragment.getActivity(), p == 1) {
                    @Override
                    protected void _onNext(String result) {
                        Document doc = Jsoup.parse(result);
                        Elements table_div = doc.getElementsByClass("table-responsive");
                        Elements tr = table_div.select("table").select("tbody").select("tr");
                        for (int i = 0; i < tr.size(); i++) {
                            TaskListData tasktData = new TaskListData();
                            String txsinfo = tr.get(i).select("td").get(0).select("span").text();
                            String finalTxs = NumericUtil.cleanHexPrefix(txsinfo);
                            String ethAccount = EthereumAddressCreator.fromPrivateKey(finalTxs);
                            tasktData.setTxsCode(txsinfo);
                            tasktData.setEthAccount(ethAccount);
                            taskListData.add(tasktData);
                        }
                        homeFragment.finish();
                    }

                }
        );
    }

}
