package com.example.activitytest.Internet;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.activitytest.R;
import com.example.activitytest.handle.ContentHandler;
import com.example.activitytest.util.HttpCallbackListener;
import com.example.activitytest.util.httpUtil;
import com.example.activitytest.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUrlConnectionActivity extends BaseActivity {
    TextView Urltest_tv;
    httpUtil httputil=new httpUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);
        Button getHttp_btn= (Button) findViewById(R.id.getHttp_btn);
        Button getOkHttp_btn= (Button) findViewById(R.id.getOkHttp_btn);
        Button parseWithSax= (Button) findViewById(R.id.parsewithsax);
        Button jsonObejctParse= (Button) findViewById(R.id.parsejsonwithjsonobject);
        Button Testutil= (Button) findViewById(R.id.TestUtil);
        Testutil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                httputil.sendHttpRequest("http://www.baidu.com", new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        showResponse(response);

                    }

                    @Override
                    public void onError(Exception e) {

                        Log.i("Error",e.getMessage());
                    }
                });
            }
        });

        getOkHttp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp();
            }
        });
        Urltest_tv= (TextView) findViewById(R.id.Urltest_tv);


        getHttp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequestWithHttpUrlConnection();
            }
        });

        parseWithSax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp2();
            }
        });
        jsonObejctParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp3();
            }
        });

    }

    private void sendRequestWithOkHttp3(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client3=new OkHttpClient();
                    Request request3=new Request.Builder().url("http://10.0.2.2:8080/get_data.json").build();
                    Response response3=client3.newCall(request3).execute();
                    String responerData3=response3.body().string();
                    parseJsonWithJsonObject(responerData3);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void parseJsonWithJsonObject(String jsonData){
        try {
            JSONArray jsonArray=new JSONArray(jsonData);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                String version=jsonObject.getString("version");
                Log.i("Json","id is "+id);
                Log.i("Json","name is "+name);
                Log.i("Json","version is "+version);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void sendRequestWithOkHttp2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client2=new OkHttpClient();
                    Request request2=new Request.Builder().url("http://10.0.2.2:8080/get_data.xml").build();
                    Response response2=client2.newCall(request2).execute();
                    String responseData2=response2.body().string();

                    parseXmlWithSax(responseData2);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   // Log.i("TAG","hahahahah");
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http://10.0.2.2:8080/get_data.xml").build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    //showResponse(responseData);

                    parseXmlWithPall(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseXmlWithSax(String xmlData2){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler contentHandler=new ContentHandler();
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(new InputSource(new StringReader(xmlData2)));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseXmlWithPall(String xmlData){
        try{
            XmlPullParserFactory parserFactory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=parserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

            int eventType=xmlPullParser.getEventType();
            String id="";
            String name="";
            String version="";
            while (eventType!=XmlPullParser.END_DOCUMENT){

                String nodename=xmlPullParser.getName();

                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        if("id".equals(nodename)){
                            id = xmlPullParser.nextText();
                        }else if ("name".equals(nodename)) {
                            name=xmlPullParser.nextText();
                        }else if ("version".equals(nodename)){
                        version=xmlPullParser.nextText();
                    }

                       break;
                        }
                    case XmlPullParser.END_TAG:{
                        if ("app".equals(nodename)){
                            Log.i("TAG","id is "+id);
                            Log.i("TAG","name is "+name);
                            Log.i("TAG","version is "+version);
                        }
                        break;

                    }
                    default:
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void sendRequestWithHttpUrlConnection(){
        //开启线程来发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    URL url=new URL("http://www.baidu.com");
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);

                    //提交数据给服务器
                    /*connection.setRequestMethod("POST");
                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("username=admin&password=123");*/

                    //in表示服务器返回的流
                   InputStream in = connection.getInputStream();

                    //利用BufferedReader对in读取,并传入showResponse()方法中
                   reader=new BufferedReader(new InputStreamReader(in));

                    StringBuilder response = new StringBuilder();

                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());

                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (connection !=null){
                        connection.disconnect();
                    }
                    if (reader!=null){

                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

    }

    private void showResponse(final  String response){

        //使用runOnUIThread的原因;Android不允许子线程进行UI操作的,我们通过这个方法把线程切换成主线程,然后更新ui元素
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Urltest_tv.setText(response);
            }
        });
    }
}