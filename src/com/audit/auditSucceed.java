package com.audit;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.http.postParams;

public class auditSucceed extends Activity{
	private TextView succeedView;
	private Button checkNowButton;
	private ArrayList<String> chooseNo=new ArrayList<String>();
	private String ip,succeed=null;
	String result=null;
	String connectURL=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auditsucceed);
		//获取选中的数据以便于发送给远程数据库，修改isReceived为1
		Intent i=getIntent();
		chooseNo=i.getStringArrayListExtra("chooseNo");
		//最后一个数定义的是成功还是失败，1表示成功
		String j=chooseNo.get(chooseNo.size()-1);
	    ipData helper=new ipData(auditSucceed.this);
		ipUser user=helper.query(1);
		ip=user.getNumber();		
		if(j.equals("1"))
			connectURL="http://"+ip+":8088/elevatorData/isCheck.php";
		else
			connectURL="http://"+ip+":8088/elevatorData/isCheckFailed.php";
		init_layout();
		chooseNo.remove(chooseNo.size()-1);
		thread.start();
	}
	private void init_layout(){

		succeedView=(TextView)findViewById(R.id.auditSucceed);
		checkNowButton=(Button)findViewById(R.id.sure);
	}
	
	Handler handler=new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(),"请检查网络", Toast.LENGTH_SHORT).show();
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
					//json数组名称为results
					if("succeed".equals(succeed))
		        	{
						succeedView.setText(R.string.AuditSucceed);
						checkNowButton.setText(R.string.GoAudit);
						checkNowButton.setOnClickListener(new auditSucceedListener());
		        	}
					else{
						succeedView.setText(R.string.DataFailed);
						checkNowButton.setVisibility(View.INVISIBLE);
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
    	//把获取的list类型的值转换成以‘,’分隔的字符串，php网页中根据‘,’再转换成数组
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
	
	class auditSucceedListener implements android.view.View.OnClickListener {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(auditSucceed.this, AuditActivity.class);
			auditSucceed.this.startActivity(intent);
		}
	}

}

