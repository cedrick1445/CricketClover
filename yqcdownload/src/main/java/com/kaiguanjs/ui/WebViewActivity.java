package com.kaiguanjs.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kaiguanjs.R;
import com.werb.permissionschecker.PermissionChecker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;


public class WebViewActivity extends Activity {
    public static final String URL = "url";
    public static final String WEB_TITLE = "webTitle";

    ProgressBar mPbLoading;
    WebView mWvContent;

//    private GestureDetector gestureDetector;
//    private int downX, downY;
    private String imgurl;

    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private PermissionChecker permissionChecker;

    public static void launch(Activity from,String url){
        Intent intent = new Intent(from, WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL, url);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yqc_webview);
        mPbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        mWvContent = (WebView) findViewById(R.id.wv_content);
        permissionChecker = new PermissionChecker(this);
//        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public void onLongPress(MotionEvent e) {
//                downX = (int) e.getX();
//                downY = (int) e.getY();
//            }
//        });
        initData();
        initListener();
    }

    private void showToast(String str){
        Toast.makeText(WebViewActivity.this,str,Toast.LENGTH_LONG).show();
    }

    public void initData() {
        mWvContent.getSettings().setJavaScriptEnabled(true);//允许执行javascript语句
        mWvContent.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWvContent.getSettings().setDefaultTextEncodingName("UTF-8");
        mWvContent.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWvContent.getSettings().setSupportMultipleWindows(true);// 多窗口
        mWvContent.getSettings().setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        mWvContent.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWvContent.getSettings().setDomStorageEnabled(true);
        //支持屏幕缩放
        mWvContent.getSettings().setSupportZoom(false);
        mWvContent.getSettings().setBuiltInZoomControls(false);
        //自适应屏幕
        mWvContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        mWvContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//自适应屏幕
        mWvContent.getSettings().setLoadWithOverviewMode(true);
//        mWvContent.getSettings().setLoadsImagesAutomatically(true);
        WebSettings webseting = mWvContent.getSettings();
        webseting.setDomStorageEnabled(true);
        webseting.setAppCacheMaxSize(1024*1024*8);//设置缓冲大小，我设的是8M
        String appCacheDir = this.getApplicationContext().getDir("cache", MODE_PRIVATE).getPath();
        webseting.setAppCachePath(appCacheDir);
        webseting.setAllowFileAccess(true);
        webseting.setAppCacheEnabled(true);
        webseting.setCacheMode(WebSettings.LOAD_DEFAULT);
        //解决Android5.0后版本的web图片显示不了的问题
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
//            mWvContent.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
        mWvContent.getSettings().setBlockNetworkImage(true);

        // 支持获取手势焦点
        mWvContent.requestFocusFromTouch();

        // 先不要自动加载图片，等页面finish后再发起图片加载
        if (Build.VERSION.SDK_INT >= 19) {
            mWvContent.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWvContent.getSettings().setLoadsImagesAutomatically(false);
        }
        mWvContent.setDownloadListener(new MyWebViewDownLoadListener());

        mWvContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mPbLoading.setProgress(newProgress);
                if(newProgress ==100){
                    mWvContent.getSettings().setBlockNetworkImage(false);
                }
            }
            @Override
            public boolean onCreateWindow(WebView view,boolean isDialog,boolean isUserGesture, Message resultMsg) {
                WebView newWebView =new WebView(WebViewActivity.this);
//                view.addView(newWebView);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();
                newWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        mWvContent.loadUrl(url);
                        return true;
                    }
                });
                return true;
            }
        });

        String url = getIntent().getStringExtra(URL);
        mWvContent.loadUrl(url);

    }

    public void initListener() {
        WebSettings settings = mWvContent.getSettings();
        settings.setJavaScriptEnabled(true);

        mWvContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView.HitTestResult result = ((WebView)v).getHitTestResult();
                if (null == result)
                    return false;
                int type = result.getType();
                if (type == WebView.HitTestResult.UNKNOWN_TYPE)
                    return false;
                if (type == WebView.HitTestResult.EDIT_TEXT_TYPE) {
                    //let TextViewhandles context menu return true;
                }
                // Setup custom handlingdepending on the type
                switch (type) {
                    case WebView.HitTestResult.PHONE_TYPE: // 处理拨号
                        break;
                    case WebView.HitTestResult.EMAIL_TYPE: // 处理Email
                        break;
                    case WebView.HitTestResult.GEO_TYPE:
                        break;
                    case WebView.HitTestResult.SRC_ANCHOR_TYPE: // 超链接
                        // Log.d(DEG_TAG, "超链接");
                        break;
                    case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
                        break;
                    case WebView.HitTestResult.IMAGE_TYPE: // 处理长按图片的菜单项
                        imgurl = result.getExtra();
                        //通过GestureDetector获取按下的位置，来定位PopWindow显示的位置
                        dialogList();
                        break;
                    default:
                        break;
                }

                return true;
            }
        });

        mWvContent.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mPbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mPbLoading.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
                String message = "SSL Certificate error.";
                switch (error.getPrimaryError()) {
                    case SslError.SSL_UNTRUSTED:
                        message = "The certificate authority is not trusted.";
                        break;
                    case SslError.SSL_EXPIRED:
                        message = "The certificate has expired.";
                        break;
                    case SslError.SSL_IDMISMATCH:
                        message = "The certificate Hostname mismatch.";
                        break;
                    case SslError.SSL_NOTYETVALID:
                        message = "The certificate is not yet valid.";
                        break;
                }
                message += " Do you want to continue anyway?";

                builder.setTitle("SSL Certificate Error");
                builder.setMessage(message);
                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http") || url.startsWith("https")) { //http和https协议开头的执行正常的流程
                    return super.shouldOverrideUrlLoading(view, url);
                } else { //其他的URL则会开启一个Acitity然后去调用原生APP
                    try{
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(in);
                    }catch (Exception e){
                        showToast("跳转失败！");
                    }
                    return true;
                }
            }
        });

        mWvContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_BACK && mWvContent.canGoBack()) {  //表示按返回键
                        mWvContent.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private void dialogList() {
        final String items[] = {"保存图片", "取消"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this,3);
//        builder.setIcon(R.mipmap.ic_launcher);
        // 设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if("保存图片".equals(items[which])){
                    if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                        permissionChecker.requestPermissions();
                    } else {
                        //检查是否有权限未被获取，有则引导去请求获取，没有则进行我们获取到相应权限后的业务逻辑
                        new SaveImage().execute(); // Android 4.0以后要使用线程来访问网络
                    }
                }

            }
        });
        builder.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {
                    //此处是我们获取权限后的业务逻辑
                    new SaveImage().execute();
                } else {
                    permissionChecker.showDialog();
                }
                break;
        }
    }

    private class SaveImage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String sdcard = Environment.getExternalStorageDirectory().toString();
                File file = new File(sdcard + "/Download");
                if (!file.exists()) {
                    file.mkdirs();
                }
                int idx = imgurl.lastIndexOf(".");
                String ext = imgurl.substring(idx);
                file = new File(sdcard + "/Download/" + new Date().getTime() + ext);
                InputStream inputStream = null;
                java.net.URL url = new URL(imgurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(20000);
                if (conn.getResponseCode() == 200) {
                    inputStream = conn.getInputStream();
                }
                byte[] buffer = new byte[4096];
                int len = 0;
                FileOutputStream outStream = new FileOutputStream(file);
                while ((len = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                outStream.close();
//                result = "图片已保存至：" + file.getAbsolutePath();
                result = "保存成功";
            } catch (Exception e) {
                result = "保存失败！" + e.getLocalizedMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showToast(result);
        }
    }

    private static long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if(mWvContent.canGoBack()){
                mWvContent.goBack();
            }else {
                if((System.currentTimeMillis()-exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
                {
                    Toast.makeText(getApplicationContext(), "再按一次退出程序",Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                }else{
                    finish();
                    System.exit(0);
                }
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
