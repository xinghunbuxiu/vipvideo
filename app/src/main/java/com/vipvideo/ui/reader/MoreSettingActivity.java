package com.vipvideo.ui.reader;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.lixh.base.BaseActivity;
import com.lixh.ireader.R;
import com.lixh.view.UToolBar;

import butterknife.Bind;
import lixh.ireader.config.ReadSettingManager;

/**
 * Created by newbiechen on 17-6-6.
 * 阅读界面的更多设置
 */

public class MoreSettingActivity extends BaseActivity {
    @Bind(R.id.more_setting_rl_volume)
    RelativeLayout mRlVolume;
    @Bind(R.id.more_setting_sc_volume)
    SwitchCompat mScVolume;
    @Bind(R.id.more_setting_rl_full_screen)
    RelativeLayout mRlFullScreen;
    @Bind(R.id.more_setting_sc_full_screen)
    SwitchCompat mScFullScreen;
    @Bind(R.id.more_setting_rl_convert_type)
    RelativeLayout mRlConvertType;
    @Bind(R.id.more_setting_sc_convert_type)
    Spinner mScConvertType;
    private ReadSettingManager mSettingManager;
    private boolean isVolumeTurnPage;
    private boolean isFullScreen;
    private int convertType;

    public void init(Bundle savedInstanceState) {
        mSettingManager = ReadSettingManager.getInstance();
        isVolumeTurnPage = mSettingManager.isVolumeTurnPage();
        isFullScreen = mSettingManager.isFullScreen();
        convertType = mSettingManager.getConvertType();
        initWidget();
        initClick();
    }

    protected void initWidget() {
        initSwitchStatus();
    }

    private void initSwitchStatus() {
        mScVolume.setChecked(isVolumeTurnPage);
        mScFullScreen.setChecked(isFullScreen);
    }

    protected void initClick() {
        mRlVolume.setOnClickListener(
                (v) -> {
                    if (isVolumeTurnPage) {
                        isVolumeTurnPage = false;
                    } else {
                        isVolumeTurnPage = true;
                    }
                    mScVolume.setChecked(isVolumeTurnPage);
                    mSettingManager.setVolumeTurnPage(isVolumeTurnPage);
                }
        );

        mRlFullScreen.setOnClickListener(
                (v) -> {
                    if (isFullScreen) {
                        isFullScreen = false;
                    } else {
                        isFullScreen = true;
                    }
                    mScFullScreen.setChecked(isFullScreen);
                    mSettingManager.setFullScreen(isFullScreen);
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mScConvertType.setAdapter(adapter);

        // initSwitchStatus() be called earlier than onCreate(), so setSelection() won't work
        mScConvertType.setSelection(convertType);

        mScConvertType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSettingManager.setConvertType(position);
                convertType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setTitle("阅读设置");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_more_setting;
    }

}
