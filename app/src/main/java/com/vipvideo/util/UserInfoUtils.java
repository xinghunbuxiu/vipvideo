package com.vipvideo.util;

import com.lixh.utils.CacheUtils;
import com.vipvideo.app.UApplication;
import com.vipvideo.bean.UserBean;
import com.vipvideo.global.Constans;

/**
 * author: rsw
 * created on: 2018/10/24 上午11:50
 * description:用户管理
 */

public class UserInfoUtils {

    public static UserBean getUserInfo(){
        return (UserBean) CacheUtils.get(UApplication.getAppContext(), Constans.KEY_USER_INFO, false);
    }

    public static void saveUserInfo( UserBean userBean){
        CacheUtils.putDisk(UApplication.getAppContext(), Constans.KEY_USER_INFO, userBean);
    }

    public static boolean isLogin(){
        return getUserInfo() != null;
    }
}