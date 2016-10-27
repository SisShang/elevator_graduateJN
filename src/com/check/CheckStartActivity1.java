package com.check;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.http.postParams;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckStartActivity1  extends ActionBarActivity {
	private String ip,speedTime,speed1,speed2, speed3,isOK;
	String connectURL=null;
	private String result=null;
	private String reportNo=null;
	private String succeed=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_start);
	    ipData helper=new ipData(CheckStartActivity1.this);
		ipUser ipuser=helper.query(1);
		ip=ipuser.getNumber();	
		connectURL="http://"+ip+":8088/elevatorData/checkSpeed1.php";
		Intent i=getIntent();
		reportNo=i.getStringExtra("reportNo");
		init_layout();
		
	}
	private void init_layout()
	{
		TextView carSafeView=(TextView)findViewById(R.id.carSafe);
		carSafeView.setText(R.string.CarSafe);
		TextView reportNoView=(TextView)findViewById(R.id.reportNo);
		reportNoView.setText(R.string.ReportNo);
		TextView speedTimeView=(TextView)findViewById(R.id.speedTime);
		speedTimeView.setText(R.string.SpeedTime);
		TextView speed1View=(TextView)findViewById(R.id.speed1);
		speed1View.setText(R.string.Speed1);
		TextView speed2View=(TextView)findViewById(R.id.Speed2);
		speed2View.setText(R.string.Speed2);
		TextView speed3View=(TextView)findViewById(R.id.speed3);
		speed3View.setText(R.string.Speed3);
		TextView isOKView=(TextView)findViewById(R.id.isOK);
		isOKView.setText(R.string.IsOK);

		final EditText reportNoEdit = (EditText)findViewById(R.id.reportNoIn);
		reportNoEdit.setTextColor(Color.parseColor("#8B4513"));
		reportNoEdit.setText(reportNo.trim());
		reportNoEdit.setEnabled(false);
		Button nextButton=(Button)findViewById(R.id.next);
		nextButton.setText(R.string.Next);
		nextButton.setOnClickListener(new nextListener());
	}
	private Handler mHandler = new Handler() {
        // 重写handleMessage()方法，此方法在UI线程运行
        @Override
        public void handleMessage(Message msg) {
        	
        	switch(msg.what)
			{
				case 1:
					Toast.makeText(getApplicationContext(),"请检查网络", Toast.LENGTH_SHORT).show();
					break;
				default:
					String result=(String)msg.obj;
					JSONObject jsonObject;
					try {
						jsonObject = new JSONObject(result);
						succeed=jsonObject.getString("results");
						System.out.println("ok1"+succeed);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if("succeed".equals(succeed))
		        	{
						
						Toast.makeText(CheckStartActivity1.this, "提交成功", Toast.LENGTH_LONG).show();
					
						Intent intent=new Intent();
						intent.setClass(CheckStartActivity1.this, CheckStartActivity2.class);
						intent.putExtra("reportNo", reportNo);
						CheckStartActivity1.this.startActivity(intent);
					
					}else{
						Toast.makeText(CheckStartActivity1.this, "提交失败", Toast.LENGTH_LONG).show();
					}
					
		        }
        }
        
    };
    class nextListener implements android.view.View.OnClickListener {
    	
    	@Override
    	public void onClick(View v) {
    		
    		getEdit();
    		if (TextUtils.isEmpty(reportNo) ) {  
                Toast.makeText(CheckStartActivity1.this, "请填写完整", Toast.LENGTH_LONG).show();
    		}else{
    			new Thread() {
    	            @Override
    	            public void run() { 
    	            	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
    	        		params.add(new BasicNameValuePair("reportNo",reportNo));
    	        		params.add(new BasicNameValuePair("speedTime",speedTime));
    	        		params.add(new BasicNameValuePair("speed1",speed1));
    	        		params.add(new BasicNameValuePair("speed2",speed2));
    	        		params.add(new BasicNameValuePair("speed3",speed3));
    	        		params.add(new BasicNameValuePair("isOK",isOK));
    	        		postParams getS=new postParams();
    	        		result=getS.dePost(connectURL, params);
    	            	try{
    	            		Message message=mHandler.obtainMessage();
    						message.obj=result;
    						mHandler.sendMessage(message);
    	            	}catch (Exception e) {
    						// TODO Auto-generated catch block
    	            		mHandler.obtainMessage(1).sendToTarget();
    	            		e.printStackTrace();
    	            	}
    	    	        
    	            }
    	        }.start();
    		}
    	}
    }
	private void getEdit(){
			// TODO Auto-generated method stub
			final EditText speedTimeEdit = (EditText)findViewById(R.id.speedTimeIn);
			speedTime = speedTimeEdit.getText().toString();
			final EditText addressEdit = (EditText)findViewById(R.id.speed1In);
			speed1 = addressEdit.getText().toString();
			final EditText unitEdit = (EditText)findViewById(R.id.speed2In);
			speed2 = unitEdit.getText().toString();
			final EditText contactEdit = (EditText)findViewById(R.id.speed3In);
			speed3 = contactEdit.getText().toString();
			EditText isOKEdit = (EditText)findViewById(R.id.isOKIn);
			isOK=isOKEdit.getText().toString();
		
		} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
