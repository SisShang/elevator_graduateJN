package com.check;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adapter.MyAdapter;
import com.check.CheckChooseActivity.checkNowListener;
import com.check.CheckChooseActivity.missionReceiveListener;
import com.database.Shujuku;
import com.database.User;
import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.http.postParams;

//Ŀǰ��δʵ��ѡ�е�reportNo���͸�����������������php����Ҳ��ûд
public class downloadSucceed extends Activity{
	private TextView succeedView;
	private Button checkNowButton;
	private ArrayList<String> chooseNo=new ArrayList<String>();
	private String ip,succeed=null;
	String result=null;
	String connectURL=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_succeed);
	    ipData helper=new ipData(downloadSucceed.this);
		ipUser ipuser=helper.query(1);
		ip=ipuser.getNumber();	
		connectURL="http://"+ip+":8088/elevatorData/download.php";
		//��ȡѡ�е������Ա��ڷ��͸�Զ�����ݿ⣬�޸�isReceivedΪ1
		Intent i=getIntent();
		chooseNo=i.getStringArrayListExtra("chooseNo");
		init_layout();
		thread.start();
	}
	private void init_layout(){

		succeedView=(TextView)findViewById(R.id.succeed);
		checkNowButton=(Button)findViewById(R.id.checkNow);
	}
	
	Handler handler=new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(),"��������", Toast.LENGTH_SHORT).show();
				break;

			default:
				String result=(String)msg.obj;
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(result);
					succeed=jsonObject.getString("results");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					//json��������Ϊresults
					if("succeed".equals(succeed))
		        	{
						succeedView.setText(R.string.Succeed);
						checkNowButton.setText(R.string.CheckNow);
						checkNowButton.setOnClickListener(new checkNowListener());
		        	}
					else{
						succeedView.setText(R.string.Failed);
						checkNowButton.setText(R.string.goBack);
						checkNowButton.setOnClickListener(new goBackListener());
					}
				break;
			}
			
		}
	};
	Thread thread=new Thread(new Runnable() {
		
		@Override
		public void run() {
			System.out.println(chooseNo);
		String params = null;
		StringBuffer sb=new StringBuffer();
    	//�ѻ�ȡ��list���͵�ֵת�����ԡ�,���ָ����ַ�����php��ҳ�и��ݡ�,����ת��������
		for(int i=0;i<chooseNo.size();i++)
		{
			sb.append(chooseNo.get(i));
			sb.append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		params=sb.toString().trim();
		System.out.println(params);
		postParams getS=new postParams();
    	result=getS.dePostS(connectURL, params);
			try {
				Message message=handler.obtainMessage();
				message.obj=result;
				handler.sendMessage(message);
			} catch (Exception e) {
				// TODO: handle exception
				handler.obtainMessage(1).sendToTarget();
			}
			
		}
	});
	
	class checkNowListener implements android.view.View.OnClickListener {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(downloadSucceed.this, checkGoActivity.class);
			downloadSucceed.this.startActivity(intent);
		}
	}
	class goBackListener implements android.view.View.OnClickListener {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(downloadSucceed.this, missionReceiveActivity.class);
			downloadSucceed.this.startActivity(intent);
		}
	}

}
