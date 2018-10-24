package com.vipvideo.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.lixh.base.BaseFragment;
import com.lixh.base.Page;
import com.lixh.base.adapter.recycleview.BaseDelegateAdapter;
import com.lixh.base.adapter.recycleview.BaseViewHolder;
import com.lixh.utils.LoadingTip;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.api.constant.Constant;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.bean.MovieTypeBean.TopcondsBean;
import com.vipvideo.presenter.VideoPresenter;
import com.vipvideo.ui.video.AllVideoActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class VipFragment extends BaseFragment<VideoPresenter> {
    Page.Builder builder;
    Page page;
    private List<DelegateAdapter.Adapter> mAdapters;
    VirtualLayoutManager layoutManager;
    DelegateAdapter delegateAdapter;
    Banner mBanner;
    VideoPresenter presenter;
    BaseDelegateAdapter tagAdapter;
    //种类
    List<TopcondsBean> tags = new ArrayList<TopcondsBean>();

    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setVisibility(View.GONE);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        presenter = getPresenter();
        initVLayout();
        builder = new Page.Builder<DelegateAdapter.Adapter>(activity).setPullLoadMore(true).setRefresh(true).setDivideHeight(R.dimen.space_1).setLoadTip(tip);
        builder.setOnLoadingListener(onLoadingListener).setAutoRefresh(false);
        builder.setAdapter(delegateAdapter).setLayoutManager(layoutManager);
        page = builder.Build(Page.PageType.Custom);
        layout.setContentView(page.getRootView());
        presenter.getAllMovieType();
        page.onRefresh();
    }

    private void initVLayout() {
        mAdapters = new LinkedList<>();
        //初始化
        //创建VirtualLayoutManager对象
        layoutManager = new VirtualLayoutManager(activity);
        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        //设置适配器
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        //自定义各种不同适配器
        initAllTypeView();
        //设置适配器
        delegateAdapter.setAdapters(mAdapters);
    }

    /**
     * 添加不同类型数据布局
     */
    private void initAllTypeView() {
        initBannerView();
        initSecondView();
        initListFirstView();
        initFirstAdView();
        initListSecondView();
        initSecondAdView();
        initListThirdView();
        initListFourView();
        initListFiveView();
        initListSixView();
    }

    private void initBannerView() {
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("http://bpic.wotucdn.com/11/66/23/55bOOOPIC3c_1024.jpg!/fw/780/quality/buy_90/unsharp/true/compress/true/watermark/url/L2xvZ28ud2F0ZXIudjIucG5n/repeat/true");
        arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505470629546&di=194a9a92bfcb7754c5e4d19ff1515355&imgtype=0&src=http%3A%2F%2Fpics.jiancai.com%2Fimgextra%2Fimg01%2F656928666%2Fi1%2FT2_IffXdxaXXXXXXXX_%2521%2521656928666.jpg");
        arrayList.add("http://bpic.wotucdn.com/11/66/23/55bOOOPIC3c_1024.jpg!/fw/780/quality/buy_90/unsharp/true/compress/true/watermark/url/L2xvZ28ud2F0ZXIudjIucG5n/repeat/true");
        arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505470629546&di=194a9a92bfcb7754c5e4d19ff1515355&imgtype=0&src=http%3A%2F%2Fpics.jiancai.com%2Fimgextra%2Fimg01%2F656928666%2Fi1%2FT2_IffXdxaXXXXXXXX_%2521%2521656928666.jpg");
        //banner
        //banner
        BaseDelegateAdapter adapter = new BaseDelegateAdapter(activity, new LinearLayoutHelper(), R.layout.home_first_banner, 1, Constant.viewType.typeBanner) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                // 绑定数据
                mBanner = (Banner) holder.getView(R.id.banner);
                //设置banner样式
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                mBanner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load((String) path).into(imageView);
                    }
                });
                //设置图片集合
                mBanner.setImages(arrayList);
                //设置banner动画效果
                mBanner.setBannerAnimation(Transformer.RotateDown);
                //设置标题集合（当banner样式有显示title时）
//                mBanner.setBannerTitles("woshinnnige");
                //设置轮播时间
                mBanner.setDelayTime(1500);
                //设置指示器位置（当banner模式中有指示器时）
                mBanner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                mBanner.start();

            }
        };
        mAdapters.add(adapter);
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) {        //开始轮播
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {     //结束轮播
            mBanner.stopAutoPlay();
        }
    }

    //筛选

    private void initSecondView() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        tagAdapter = new BaseDelegateAdapter(activity, gridLayoutHelper, R.layout.home_sencond_layout, tags.size(), Constant.viewType.typeView) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                TopcondsBean bean = tags.get(position);
                holder.setText(R.id.tv_tag, bean.getTitle());
                holder.setOnItemViewClickListener((v) -> {
                    intent.withString("type", bean.getType()).withString("order", bean.getOrder()).go(AllVideoActivity.class);
                });
            }
        };
        mAdapters.add(tagAdapter);
    }


    private void initListFirstView() {
        initTitleView(1);
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        BaseDelegateAdapter adapter = new BaseDelegateAdapter(activity, gridLayoutHelper, R.layout.home_vlayout_grid, 4, Constant.viewType.typeGv) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final ImageView ivImage = (ImageView) holder.getView(R.id.iv_image);
                ivImage.setBackgroundResource(R.drawable.black_background);
            }
        };
        mAdapters.add(adapter);
        initMoreView(1);
    }


    private void initFirstAdView() {
        BaseDelegateAdapter adAdapter = new BaseDelegateAdapter(activity, new LinearLayoutHelper(), R.layout.home_first_ad, 1, Constant.viewType.typeAd) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        };
        mAdapters.add(adAdapter);
    }


    private void initListSecondView() {
        initTitleView(2);
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setAspectRatio(4.0f);
        linearLayoutHelper.setDividerHeight(5);
        linearLayoutHelper.setMargin(0, 0, 0, 0);
        linearLayoutHelper.setPadding(0, 0, 0, 10);
        BaseDelegateAdapter adapter = new BaseDelegateAdapter(activity, linearLayoutHelper, R.layout.home_vlayout_news, 3, Constant.viewType.typeList2) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        };
        mAdapters.add(adapter);
        initMoreView(2);
    }


    private void initSecondAdView() {
        BaseDelegateAdapter adAdapter = new BaseDelegateAdapter(activity, new LinearLayoutHelper(), R.layout.home_first_ad, 1, Constant.viewType.typeAd2) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.getView(R.id.iv_image_ad).setBackgroundResource(R.drawable.bg_small_solda_min);

            }
        };
        mAdapters.add(adAdapter);
    }


    private void initListThirdView() {
        initTitleView(3);
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        BaseDelegateAdapter adapter = new BaseDelegateAdapter(activity,
                gridLayoutHelper, R.layout.home_vlayout_grid, 2, Constant.viewType.typeGv3) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ImageView ivImage = (ImageView) holder.getView(R.id.iv_image);
                ivImage.setImageResource(R.drawable.bg_small_autumn_tree_min);
            }
        };
        mAdapters.add(adapter);
        initMoreView(3);
    }


    private void initListFourView() {
        initTitleView(4);
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setAspectRatio(4.0f);
        linearLayoutHelper.setDividerHeight(5);
        linearLayoutHelper.setMargin(0, 0, 0, 0);
        linearLayoutHelper.setPadding(0, 0, 0, 10);
        BaseDelegateAdapter adapter = new BaseDelegateAdapter(activity, linearLayoutHelper, R.layout.home_vlayout_news, 3, Constant.viewType.typeList4) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

            }
        };
        mAdapters.add(adapter);
        initMoreView(4);
    }


    private void initListFiveView() {
        initTitleView(5);
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        BaseDelegateAdapter adapter = new BaseDelegateAdapter(activity, gridLayoutHelper, R.layout.home_vlayout_grid, 6, Constant.viewType.typeGvBottom) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ImageView ivImage = (ImageView) holder.getView(R.id.iv_image);
                ivImage.setImageResource(R.drawable.bg_small_solda_min);
            }
        };
        mAdapters.add(adapter);
        initMoreView(4);
    }


    private void initListSixView() {
        initTitleView(6);
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setAspectRatio(4.0f);
        linearLayoutHelper.setDividerHeight(5);
        linearLayoutHelper.setMargin(0, 0, 0, 0);
        linearLayoutHelper.setPadding(0, 0, 0, 10);
        BaseDelegateAdapter adapter = new BaseDelegateAdapter(activity, linearLayoutHelper, R.layout.home_vlayout_news, 3, Constant.viewType.typeList5) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

            }
        };
        mAdapters.add(adapter);
        initMoreView(6);
    }


    private void initTitleView(final int type) {
        BaseDelegateAdapter titleAdapter = new BaseDelegateAdapter(activity, new LinearLayoutHelper(), R.layout.home_vlayout_title, 1, Constant.viewType.typeTitle) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                switch (type) {
                    case 1:
                        holder.setText(R.id.tv_title, "为你精选");
                        break;
                    case 2:
                        holder.setText(R.id.tv_title, "推广专区");
                        break;
                    case 3:
                        holder.setText(R.id.tv_title, "行业动态");
                        break;
                    case 4:
                        holder.setText(R.id.tv_title, "趋势分析");
                        break;
                    case 5:
                        holder.setText(R.id.tv_title, "大牛分享");
                        break;
                    case 6:
                        holder.setText(R.id.tv_title, "潇湘剑雨");
                        break;
                    default:
                        holder.setText(R.id.tv_title, "这个是标题");
                        break;
                }
            }
        };
        mAdapters.add(titleAdapter);
    }


    private void initMoreView(final int type) {
        BaseDelegateAdapter moreAdapter = new BaseDelegateAdapter(activity, new LinearLayoutHelper(), R.layout.home_vlayout_more, 1, Constant.viewType.typeMore) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                switch (type) {
                    case 1:
                        holder.setText(R.id.tv_more, "查看更多");
                        break;
                    case 2:
                        holder.setText(R.id.tv_more, "查看更多");
                        break;
                    case 3:
                        holder.setText(R.id.tv_more, "查看更多");
                        break;
                    case 4:
                        holder.setText(R.id.tv_more, "查看更多");
                        break;
                    case 5:
                        holder.setText(R.id.tv_more, "查看更多");
                        break;
                    case 6:
                        holder.setText(R.id.tv_more, "没有更多数据");
                        break;
                    default:
                        holder.setText(R.id.tv_more, "查看更多");
                        break;
                }
            }
        };
        mAdapters.add(moreAdapter);
    }


    Page.OnLoadingListener onLoadingListener = new Page.OnLoadingListener() {
        @Override
        public void load(int page, final Page.OnLoadFinish onLoadFinish) {
            presenter.getAllMovieType();
            finish();
        }
    };

    public void finish() {
        page.finish(null, LoadingTip.LoadStatus.FINISH);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    //类型填充
    public void setMovieTypeBean(MovieTypeBean movieTypeBean) {
        TopcondsBean all = movieTypeBean.getTopconds().get(0);//全部
        List<TopcondsBean> list = movieTypeBean.getTopconds().subList(3, movieTypeBean.getTopconds().size());
        list.add(all);
        if (tags.size() > 0) {
            tags.clear();
        }
        tags.addAll(list);
    }
}
