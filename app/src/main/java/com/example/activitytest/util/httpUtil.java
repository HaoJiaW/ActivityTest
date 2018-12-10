package com.example.activitytest.util;

import java.net.HttpURLConnection;

/*
* *网络请求的封装类
 */

import com.example.activitytest.Internet.HttpUrlConnectionActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class httpUtil  {

    public static void sendHttpRequest(final String adress,final HttpCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;

                try{
                    URL url=new URL(adress);
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);

                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    //return response.toString();
                    if (listener!=null){
                        listener.onFinish(response.toString());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    //  return e.getMessage();
                listener.onError(e);
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}