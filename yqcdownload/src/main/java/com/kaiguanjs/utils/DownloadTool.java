package com.kaiguanjs.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.kaiguanjs.R;

import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.FileDownloadConfiguration;
import org.wlf.filedownloader.FileDownloader;
import org.wlf.filedownloader.listener.OnDeleteDownloadFileListener;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener;

import java.io.File;


public class DownloadTool {
    private static boolean isFinishDownLoad = true;

    public static void download(final Activity activity, final String url) {
        ///复制到/data/data/package_name/files/目录下文件名
        String dir = activity.getExternalCacheDir().getAbsolutePath() + File.separator + "FileDownloader";
        String dirapk = dir + url.substring(url.lastIndexOf("/"));
        File apkfile = new File(dirapk);
        if(apkfile.exists()){
            install(activity,apkfile);
        }else {
            downloadapk(activity,url,dir);
        }
    }

    private static void downloadapk(final Activity activity, final String url, String dir) {

        if(isFinishDownLoad){
            isFinishDownLoad = false;
        }else {
            return;
        }

        // 1、创建Builder
        FileDownloadConfiguration.Builder builder = new FileDownloadConfiguration.Builder(activity);

        // 2.配置Builder
        // 配置下载文件保存的文件夹
        builder.configFileDownloadDir(dir);
        // 配置同时下载任务数量，如果不配置默认为2
        builder.configDownloadTaskSize(3);
        // 配置失败时尝试重试的次数，如果不配置默认为0不尝试
        builder.configRetryDownloadTimes(5);
        // 开启调试模式，方便查看日志等调试相关，如果不配置默认不开启
        builder.configDebugMode(false);
        // 配置连接网络超时时间，如果不配置默认为15秒
        builder.configConnectTimeout(25000);// 25秒

        // 3、使用配置文件初始化FileDownloader
        FileDownloadConfiguration configuration = builder.build();
        FileDownloader.init(configuration);

        final Dialog downDialog = new Dialog(activity, R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        View view = View.inflate(activity,R.layout.ui_yqc_download_progress1,null);
//        View view = View.inflate(activity,R.layout.ui_yqc_download_progress,null);
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        downDialog.setCancelable(false);// 设置是否可以通过点击Back键取消
        downDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        downDialog.addContentView(view,params);
        downDialog.show();

        final ProgressBar pb = (ProgressBar)view.findViewById(R.id.progress_bar);
        final TextView tvProgress = (TextView)view.findViewById(R.id.tv_progress);

        //设置对话框铺满屏幕
        Window win = downDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);

        OnFileDownloadStatusListener mOnFileDownloadStatusListener = new OnSimpleFileDownloadStatusListener() {
            @Override
            public void onFileDownloadStatusRetrying(DownloadFileInfo downloadFileInfo, int retryTimes) {
                // 正在重试下载（如果你配置了重试次数，当一旦下载失败时会尝试重试下载），retryTimes是当前第几次重试
            }

            @Override
            public void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo) {
                // 等待下载（等待其它任务执行完成，或者FileDownloader在忙别的操作）
            }

            @Override
            public void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo) {
                // 准备中（即，正在连接资源）
            }

            @Override
            public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
                // 已准备好（即，已经连接到了资源）
            }

            @Override
            public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long
                    remainingTime) {
                // 正在下载，downloadSpeed为当前下载速度，单位KB/s，remainingTime为预估的剩余时间，单位秒
                int totalSize = (int) downloadFileInfo.getFileSizeLong();
                int downloaded = (int) downloadFileInfo.getDownloadedSizeLong();
                float temp = (float) downloaded / (float) totalSize;
                int progress = (int) (temp * 100);
                pb.setProgress(progress);
                tvProgress.setText(String.format("正在更新,请稍后... %s%%",progress));
//                downDialog.setProgress(progress);
            }

            @Override
            public void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo) {
                // 下载已被暂停
            }

            @Override
            public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
                // 下载完成（整个文件已经全部下载完成）
                isFinishDownLoad = true;
                if (downDialog != null && downDialog.isShowing()) {
                    downDialog.dismiss();
                    installApk(activity,downloadFileInfo.getFilePath());
//                    installAPK(activity, new File(downloadFileInfo.getFilePath()));
                }
            }

            @Override
            public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, FileDownloadStatusFailReason failReason) {
                // 下载失败了，详细查看失败原因failReason，有些失败原因你可能必须关心

                String failType = failReason.getType();
                String failUrl = failReason.getUrl();// 或：failUrl = url，url和failReason.getUrl()会是一样的

                if (FileDownloadStatusFailReason.TYPE_URL_ILLEGAL.equals(failType)) {
                    // 下载failUrl时出现url错误
                } else if (FileDownloadStatusFailReason.TYPE_STORAGE_SPACE_IS_FULL.equals(failType)) {
                    // 下载failUrl时出现本地存储空间不足
                } else if (FileDownloadStatusFailReason.TYPE_NETWORK_DENIED.equals(failType)) {
                    // 下载failUrl时出现无法访问网络
                } else if (FileDownloadStatusFailReason.TYPE_NETWORK_TIMEOUT.equals(failType)) {
                    // 下载failUrl时出现连接超时
                } else {
                    // 更多错误....
                }

                // 查看详细异常信息
                Throwable failCause = failReason.getCause();// 或：failReason.getOriginalCause()

                // 查看异常描述信息
                String failMsg = failReason.getMessage();// 或：failReason.getOriginalCause().getMessage()
                if(failMsg.contains("Trust anchor for certification path not found")){
//                    hygdload(checkData, canDismissDialog);
                    //https下载失败再重新下载，第二次就可以信任所有服务器成功
                    FileDownloader.start(url);
                }else {
                    if (downDialog != null && downDialog.isShowing()) {
                        downDialog.dismiss();
                    }
                    goToBrowser(activity,url, true);
                }
            }
        };
        FileDownloader.registerDownloadStatusListener(mOnFileDownloadStatusListener);

        // 创建一个自定义保存路径和文件名称的下载
//        FileDownloader.detect(url, new OnDetectBigUrlFileListener() {
//            @Override
//            public void onDetectNewDownloadFile(String url, String fileName, String saveDir, long fileSize) {
//                // 如果有必要，可以改变文件名称fileName和下载保存的目录saveDir
//                FileDownloader.createAndStart(url, saveDir, fileName);
//            }
//
//            @Override
//            public void onDetectUrlFileExist(String url) {
//                // 继续下载，自动会断点续传（如果服务器无法支持断点续传将从头开始下载）
//                FileDownloader.start(url);
//            }
//
//            @Override
//            public void onDetectUrlFileFailed(String url, OnDetectBigUrlFileListener.DetectBigUrlFileFailReason failReason) {
//                // 探测一个网络文件失败了，具体查看failReason
//            }
//        });

//        OnDownloadFileChangeListener mOnDownloadFileChangeListener = new OnDownloadFileChangeListener() {
//            @Override
//            public void onDownloadFileCreated(DownloadFileInfo downloadFileInfo) {
//                // 一个新下载文件被创建，也许你需要同步你自己的数据存储，比如在你的业务数据库中增加一条记录
//            }
//            @Override
//            public void onDownloadFileUpdated(DownloadFileInfo downloadFileInfo, Type type) {
//                // 一个下载文件被更新，也许你需要同步你自己的数据存储，比如在你的业务数据库中更新一条记录
//            }
//            @Override
//            public void onDownloadFileDeleted(DownloadFileInfo downloadFileInfo) {
//                // 一个下载文件被删除，也许你需要同步你自己的数据存储，比如在你的业务数据库中删除一条记录
//            }
//        };
//        FileDownloader.registerDownloadFileChangeListener(mOnDownloadFileChangeListener);


        // 删除单个下载文件
        FileDownloader.delete(url, true, new OnDeleteDownloadFileListener() {
            @Override
            public void onDeleteDownloadFilePrepared(DownloadFileInfo downloadFileInfo) {

            }

            @Override
            public void onDeleteDownloadFileSuccess(DownloadFileInfo downloadFileInfo) {
//                ToastUtil.showMessage("删除成功");
                FileDownloader.start(url);
            }

            @Override
            public void onDeleteDownloadFileFailed(DownloadFileInfo downloadFileInfo, DeleteDownloadFileFailReason deleteDownloadFileFailReason) {
//                ToastUtil.showMessage("删除失败" + deleteDownloadFileFailReason.getMessage());
                FileDownloader.start(url);
            }
        });
        // 第四步、下载文件和管理文件（FileDownloader API的简单使用）
        // 如果文件没被下载过，将创建并开启下载，否则继续下载，自动会断点续传（如果服务器无法支持断点续传将从头开始下载）
//        FileDownloader.start(url);
//        FileDownloader.reStart(url);
    }

    /**
     * 安装apk应用
     */
    private static void installAPK(Activity activity, File apkFile) {
        if (apkFile.isFile()) {
//            String fileName = apkFile.getName();
//            String postfix = fileName.substring(fileName.length() - 4, fileName.length());
//            if (postfix.toLowerCase(CommonUtil.getLocale()).equals(".apk")) {
//
//            }
            try{
                String cmd = "chmod 755 " + apkFile.getAbsolutePath();
                try {
                    Runtime.getRuntime().exec(cmd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Uri uri = Uri.fromFile(apkFile);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                //判断是否是AndroidN以及更高的版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileProvider", apkFile);
//                    Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                activity.startActivity(intent);
            }catch (Exception e){
                Toast.makeText(activity,"安装文件出错！",Toast.LENGTH_LONG).show();
            }
        } else if (apkFile.isDirectory()) {
            File[] files = apkFile.listFiles();
            int fileCount = files.length;
            for (int i = 0; i < fileCount; i++) {
                installAPK(activity, files[i]);
            }
        }
    }

    private static void goToBrowser(Activity activity, String url, boolean canDismissDialog) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
        if (!canDismissDialog) {
            //强制更新前不能使用APP
//            ((Activity) mActivity).finish();
        }
    }

    /**
     * 安装apk
     * @param
     */
    public static  void installApk(final Activity context, String saveFileName){
        File apkFile = new File(saveFileName);

        if (!apkFile.exists()) {
            return;
        }
        //有权限，开始安装应用程序
        install(context, apkFile);

    }

    public static void install(Context context, File file) {
        Uri apkUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", file);
        } else {
            apkUri = Uri.fromFile(file);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

//    public static String getPackageName(Context context) {
//        //当前应用pid
//        int pid = android.os.Process.myPid();
//        //任务管理类
//        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        //遍历所有应用
//        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
//        for (ActivityManager.RunningAppProcessInfo info : infos) {
//            if (info.pid == pid)//得到当前应用
//                return info.processName;//返回包名
//        }
//        return "";
//    }
}
