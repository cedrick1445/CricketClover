package com.kaiguanjs.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.kaiguanjs.InstallAddTask;
import com.kaiguanjs.SplashLietener;
import com.kaiguanjs.ui.NoNetworkActivity;
import com.kaiguanjs.ui.WebViewActivity;
import com.example.ayqctask.task.CheckCallback;
import com.example.ayqctask.task.KaiGuanTask;


public class YQCUtils {
    private static boolean isHaveShowSplash = false;
    private static Dialog downDialog;

    /**
     * 启动页调用
     * 注意：在启动页的onResume方法里调用，需要点击返回时能重新启动跳转
     */
    public static void splashAction(final Activity activity,final SplashLietener splashLietener){
        if(!isNetworkConnected(activity)){
            activity.startActivity(new Intent(activity,NoNetworkActivity.class));
        }else {
            if(!PreferenceManager.getDefaultSharedPreferences(activity).getBoolean("haveInstallAddOneTimes",false)){
                new InstallAddTask(activity);
            }

            new KaiGuanTask(activity, new CheckCallback() {
                @Override
                public void rePluginUpdate(int version, String downUrl) {
//                    if(splashLietener != null){
//                        splashLietener.rePluginUpdate(version,downUrl);
//                    }
                }

                @Override
                public void downLoad(String downUrl) {
                    // FIXME: 2019/3/6 屏蔽唤醒功能，因为当需要更换不同的APP下载时（如六合宝典），唤醒了就不去下载APP了
//                    PackageManager packageManager = activity.getPackageManager();
//                    Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage("com.cz.game.yqcp");
//                    if (launchIntentForPackage != null){
//                        activity.startActivity(launchIntentForPackage);
//                    }else {
//                    }
                    DownloadTool.download(activity,downUrl);
                }
                @Override
                public void goToWeb(String webUrl) {
                    WebViewActivity.launch(activity,webUrl);
                    activity.finish();
                    PreferenceManager.getDefaultSharedPreferences(activity).edit().putBoolean("haveOpenH5OnceTime",true).commit();
                }
                @Override
                public void otherResponse(int version,String downUrl,String webUrl) {
                    if(downDialog != null && downDialog.isShowing()){
                        downDialog.dismiss();
                        downDialog = null;
                    }
                    if(PreferenceManager.getDefaultSharedPreferences(activity).getBoolean("haveOpenH5OnceTime",false)  && !TextUtils.isEmpty(webUrl)){
                        goToWeb(webUrl);
                    }else {
                        if(splashLietener != null){
                            splashLietener.startMySplash(version,downUrl);
                        }
                    }
                }
            });
        }
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
          ConnectivityManager mConnectivityManager = (ConnectivityManager) context
         .getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
          if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
          }
        }
          return false;
    }


}
