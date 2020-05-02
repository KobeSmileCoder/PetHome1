package com.example.sunhan.pethome1.util;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sunhan.pethome1.listener.HandleResponse;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParamBean;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙汉 on 2019/01/10
 */

public class ConnectServer extends AsyncTask<String, Void, String>{

    List<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
    private String URL;
    private HandleResponse handleResponse;
    private String parameter;

    //doPost构造函数
    public ConnectServer(List<BasicNameValuePair> paramsList, String url, HandleResponse handleResponse) {
        this.paramsList = paramsList;
        this.URL = url;
        this.handleResponse = handleResponse;
    }

    @Override
    protected void onPostExecute(String s) {
        handleResponse.getResponse(s);
        super.onPostExecute(s);
    }

    //doGet构造函数
    public ConnectServer(String parameter, String url){
        this.parameter = parameter;
        this.URL = url;
    }

    @Override
    protected String doInBackground(String... strings) {
        //通过post方式通信，如果通过get,则doGet即可
        return doPost();
    }

    /**
     * 用Post方式跟服务器传递数据
     * @return url
     */
    private String doPost() {
        String responseStr = "";
        try {
            HttpPost httpRequest = new HttpPost(URL);
            //应该用requestConfig来代替HttpParams，下面的这个方法过时了，但要引入最新的包应该
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params, 10000);//从连接池中获取连接的超时时间
            HttpConnectionParams.setConnectionTimeout(params, 10000);//通过网络与服务器建立连接的超时时间
            HttpConnectionParams.setConnectionTimeout(params, 10000);//读响应数据的超时时间
            httpRequest.setParams(params);

            UrlEncodedFormEntity mUrlEncodeFormEntity = new UrlEncodedFormEntity(paramsList, HTTP.UTF_8);
            httpRequest.setEntity(mUrlEncodeFormEntity);
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            final int ret = httpResponse.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                responseStr = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
            } else {
                responseStr = "-1";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseStr;
    }
}
