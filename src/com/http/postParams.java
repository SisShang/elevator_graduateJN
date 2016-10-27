package com.http;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class postParams {
	public String dePost(String connectUrl,List<BasicNameValuePair> params){
		String mReturnConnection=null;
		DefaultHttpClient client=new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(connectUrl);
		try{
			UrlEncodedFormEntity p_entity=new UrlEncodedFormEntity(params,"utf-8");
			httpPost.setEntity(p_entity);

			HttpResponse response =client.execute(httpPost);
			if(response.getStatusLine().getStatusCode()==200){
				//取出字符串
				mReturnConnection=EntityUtils.toString(response.getEntity());
			}else{
				mReturnConnection="error";
			}
			}catch(IllegalStateException e){
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		Log.i("mReturnConnection",mReturnConnection);
		return mReturnConnection;
		
	}
	public String dePostS(String connectUrl,String params){
		String mReturnConnection=null;
		DefaultHttpClient client=new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(connectUrl);
		try{
			StringEntity p_entity=new StringEntity(params,"utf-8");
			httpPost.setEntity(p_entity);

			HttpResponse response =client.execute(httpPost);
			if(response.getStatusLine().getStatusCode()==200){
				//取出字符串
				mReturnConnection=EntityUtils.toString(response.getEntity());
			}else{
				mReturnConnection="error";
			}
			}catch(IllegalStateException e){
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		Log.i("mReturnConnection",mReturnConnection);
		return mReturnConnection;
		
	}

}
