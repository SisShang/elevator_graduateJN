package com.main;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.check.CheckStartActivity2;
import com.database.User;
import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.http.postParams;

import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class registActivity extends ActionBarActivity {
	
	private String role,username,password,unit,workNo,phoneNo=null;//str用来标记身份便于之后跳转页面
	private RadioButton checkButton,auditButton,inspectButton;
	private EditText userNameEdit,passwordEdit,unitEdit,phoneNoEdit,workNoEdit;
	String ip,succeed=null;
	String result=null;
	User user;
	String connectURL=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist); 
		role=null;
		username=null;
		password=null;
	    ipData helper=new ipData(registActivity.this);
		ipUser ipuser=helper.query(1);
		ip=ipuser.getNumber();	
		connectURL="http://"+ip+":8088/elevatorData/regist.php?";
		setTitle("用户注册");
		init_layout();     
	}

	private void init_layout()
	{ 
		TextView userNameView=(TextView)findViewById(R.id.userName);
		userNameView.setText(R.string.UserName);
		TextView passwordView=(TextView)findViewById(R.id.password);
		passwordView.setText(R.string.Password);
		TextView unitView=(TextView)findViewById(R.id.unit);
		unitView.setText(R.string.Unit);
		TextView phoneNoView=(TextView)findViewById(R.id.phoneNo);
		phoneNoView.setText(R.string.PhoneNo);
		TextView workNoView=(TextView)findViewById(R.id.workNo);
		workNoView.setText(R.string.WorkNo);
		TextView roleView=(TextView)findViewById(R.id.role);
		roleView.setText(R.string.Role);
		RadioGroup roleGroup=(RadioGroup)findViewById(R.id.roleIn);
		inspectButton=(RadioButton)findViewById(R.id.inspect);
		inspectButton.setText(R.string.Inspect);
		checkButton=(RadioButton)findViewById(R.id.check);
		checkButton.setText(R.string.Check);
		auditButton=(RadioButton)findViewById(R.id.audit);
		auditButton.setText(R.string.Audit);
		roleGroup.setOnCheckedChangeListener(mChangeRadio);
		Button registButton=(Button)findViewById(R.id.regist);
		registButton.setText(R.string.Regist);
		registButton.setOnClickListener(new registListener());
	}
	private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == checkButton.getId()) {
				// 把mRadio1的内容传到mTextView1
				role="check";
			} else if (checkedId == inspectButton.getId()) {
				// 把mRadio2的内容传到mTextView1
				role="inspect";
			}else if(checkedId == auditButton.getId()) {
				// 把mRadio2的内容传到mTextView1
				role="audit";
			}
		}
	};
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
					//只有一个对象，不需要用到jsonarray
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
						Intent intent=new Intent();
						intent.putExtra("ok", "regist");
						intent.setClass(registActivity.this, deal.class);
						registActivity.this.startActivity(intent);
			        }else if("have".equals(succeed))
		        	{
			        	Toast.makeText(getApplicationContext(),"用户名已存在", Toast.LENGTH_SHORT).show();
			        }else{
						Toast.makeText(registActivity.this, "提交失败", Toast.LENGTH_LONG).show();
		        }
		        }
        }
        
    };
 
	class registListener implements android.view.View.OnClickListener {
		
		@Override
		public void onClick(View v) {					
			getEdit();
			if (TextUtils.isEmpty(username)|| TextUtils.isEmpty(password)|| TextUtils.isEmpty(role)) {  
	            Toast.makeText(registActivity.this, "请填写完整", Toast.LENGTH_LONG).show();}
	        else if(role.equals("inspect") && TextUtils.isEmpty(unit)){
	            	Toast.makeText(registActivity.this, "请填写报检单位", Toast.LENGTH_LONG).show();
			}else{
				new Thread() {
		            @Override
		            public void run() { 
		            	
		            	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		            	//前边是标签，后边是值
		        		params.add(new BasicNameValuePair("username",username.trim()));
		        		params.add(new BasicNameValuePair("unit",unit.trim()));
		        		params.add(new BasicNameValuePair("password",password.trim()));
		        		params.add(new BasicNameValuePair("phoneNo",phoneNo.trim()));
		        		params.add(new BasicNameValuePair("workNo",workNo.trim()));
		        		params.add(new BasicNameValuePair("role",role.trim()));
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
	private void getEdit()
	{
		userNameEdit = (EditText)findViewById(R.id.userNameIn);
		username = userNameEdit.getText().toString();
		passwordEdit = (EditText)findViewById(R.id.passwordIn);
		password = passwordEdit.getText().toString();
		unitEdit = (EditText)findViewById(R.id.unitIn);
		unit = unitEdit.getText().toString();
		phoneNoEdit = (EditText)findViewById(R.id.phoneNoIn);
		phoneNo = phoneNoEdit.getText().toString();
		workNoEdit = (EditText)findViewById(R.id.workNoIn);
		workNo = workNoEdit.getText().toString();
	}

	protected void onResume(){
		super.onResume();
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


