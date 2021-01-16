package com.kaiguanjs;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 检查开关网络请求类
 * @author Neal
 * @date 2018/7/20  22:01
 */

public class InstallAddTask {

    public InstallAddTask(final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getCheckInfo(activity);
            }
        }).start();
    }

    private void getCheckInfo(Activity activity) {
        Looper.prepare();
        try {
            URL url = new URL(String.format("http://%s/jeesite/f/guestbook/install?",getURL(activity)));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //设置参数
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setDoOutput(true);     //需要输出
            urlConnection.setDoInput(true);      //需要输入
            urlConnection.setUseCaches(false);   //不允许缓存
            urlConnection.setRequestMethod("POST");      //设置POST方式连接

            //设置请求属性
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            urlConnection.setRequestProperty("Charset", "UTF-8");

            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            urlConnection.connect();

            OutputStream os = urlConnection.getOutputStream();
            os.write(getParams(activity).getBytes());
            os.flush();
            os.close();

            int code=urlConnection.getResponseCode();
            if (code==HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);

                StringBuffer buffer = new StringBuffer();
                String temp = null;

                while ((temp = bufferedReader.readLine()) != null) {
                    buffer.append(temp);
                }
                bufferedReader.close();//记得关闭
                reader.close();
                inputStream.close();
                String respontStr = buffer.toString();
                if(!TextUtils.isEmpty(respontStr)){
                    JSONObject jsonObject = new JSONObject(buffer.toString());
                    if(jsonObject.getInt("httpCode") == 200){
                        PreferenceManager.getDefaultSharedPreferences(activity).edit().putBoolean("haveInstallAddOneTimes",true).commit();
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Looper.loop();
    }

    private String getParams(Activity activity) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = activity.getApplicationContext().getPackageManager()
                    .getApplicationInfo(activity.getApplicationContext().getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String yqcid = appInfo.metaData.getString("YQCID").trim();
        return yqcid;
    }

    private String getURL(Activity activity) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = activity.getApplicationContext().getPackageManager()
                    .getApplicationInfo(activity.getApplicationContext().getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return appInfo.metaData.getString("YQCU");
    }

}
