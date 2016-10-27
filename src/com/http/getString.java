package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import android.widget.Toast;

public class getString {
	public String doGet(String URL)	{
		String mReturnConnection=null;
		BufferedReader in=null;
		try{
			//http�ͻ���
			HttpClient client=new DefaultHttpClient();
			//��������ʽΪget
			HttpGet request=new HttpGet();
			//���������ַ
			request.setURI(new URI(URL));
			//������Ӧ����
			HttpResponse response =client.execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				//���������ص�����
				mReturnConnection=EntityUtils.toString(response.getEntity(), "utf-8");
			}else{
				mReturnConnection="error";
			}
		}catch(Exception e){
			Log.e("httperror",e.toString());
		}finally{
			if(in!=null){
				try{
					in.close();
				}catch(IOException ioe){
					Log.e("httperror",ioe.toString());
				}
			}
		}
		mReturnConnection.replaceAll("\\s*", "");
		return mReturnConnection;
		
	}

}
