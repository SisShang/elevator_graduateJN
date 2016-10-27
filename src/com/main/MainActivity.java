package com.main;


import org.json.JSONException;
import org.json.JSONObject;

import com.audit.AuditActivity;
import com.check.CheckChooseActivity;
import com.check.missionReceiveActivity;
import com.database.Shujuku;
import com.database.User;
import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.http.getString;
import com.inspect.InspectActivity;

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

public class MainActivity extends ActionBarActivity {
	
	private String role,username,password,str,isLoginSucceed=null;//str���������ݱ���֮����תҳ��
	private RadioButton checkButton,auditButton,inspectButton;
	private EditText userNameEdit,passwordEdit;
	String succeed=null;
	String result=null;
	User user;
	String connectURL=null;
	private String ip=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		role=null;
		username=null;
		password=null;
		str=null;
		isLoginSucceed=null;
		setTitle("�û���¼");
		init_layout();    
/*
		Shujuku helper=new Shujuku(MainActivity.this);
		helper=new Shujuku(MainActivity.this);
		helper.deleteDatabase(MainActivity.this);
*/		
	}

	private void init_layout()
	{ 
		TextView userNameView=(TextView)findViewById(R.id.userName);
		userNameView.setText(R.string.UserName);
		TextView passwordView=(TextView)findViewById(R.id.password);
		passwordView.setText(R.string.Password);
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
		Button loginButton=(Button)findViewById(R.id.login);
		loginButton.setText(R.string.Login);
		loginButton.setOnClickListener(new loginListener());
		Button registButton=(Button)findViewById(R.id.regist);
		registButton.setText(R.string.Regist);
		Button ipButton=(Button)findViewById(R.id.ip);
		ipButton.setText(R.string.ipIn);
		registButton.setOnClickListener(new registListener());
		ipButton.setOnClickListener(new ipListener());
	}
	private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == checkButton.getId()) {
				// ��mRadio1�����ݴ���mTextView1
				role="check";
				str="1";
			} else if (checkedId == inspectButton.getId()) {
				// ��mRadio2�����ݴ���mTextView1
				role="inspect";
				str="2";
			}else if(checkedId == auditButton.getId()) {
				// ��mRadio2�����ݴ���mTextView1
				role="audit";
				str="3";
			}
		}
	};
    private Handler mHandler = new Handler() {
        // ��дhandleMessage()�������˷�����UI�߳�����
        @Override
        public void handleMessage(Message msg) {
        	switch(msg.what)
			{
				case 1:
					Toast.makeText(getApplicationContext(),"��������", Toast.LENGTH_SHORT).show();
					break;
				default:
					//ֻ��һ�����󣬲���Ҫ�õ�jsonarray
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
			        	System.out.println(result);
			        	System.out.println(succeed);
			        	isLoginSucceed="1";
			        	
			        	//�жϷ���ֵ�Ƿ�Ϊtrue�����ǵĻ���������ҳ��
						if(isLoginSucceed.equals("1") && str.equals("1")){
							Intent intent=new Intent();
							intent.setClass(MainActivity.this, CheckChooseActivity.class);
							MainActivity.this.startActivity(intent);
						}else if(isLoginSucceed.equals("1") && str.equals("2")){
							Intent intent=new Intent();
							intent.setClass(MainActivity.this, InspectActivity.class);
							MainActivity.this.startActivity(intent);
						}else if(isLoginSucceed.equals("1") && str.equals("3")){
							Intent intent=new Intent();
							intent.setClass(MainActivity.this, AuditActivity.class);
							MainActivity.this.startActivity(intent);
						}
			        }else{
						Toast.makeText(MainActivity.this, "�˺Ż��������", Toast.LENGTH_LONG).show();
		        }
		        }
        }
        
    };
 
	class loginListener implements android.view.View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			
			userNameEdit = (EditText)findViewById(R.id.userNameIn);
			username = userNameEdit.getText().toString();
			passwordEdit = (EditText)findViewById(R.id.passwordIn);
			password = passwordEdit.getText().toString();
			ipData helper=new ipData(MainActivity.this);
			ipUser user=helper.query(1);
			ip=user.getNumber();		
			connectURL="http://"+ip+":8088/elevatorData/login.php?";
			connectURL=connectURL+"role="+role+"&username="+username+"&password="+password;
			if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {  
                Toast.makeText(MainActivity.this, "�û����������벻��Ϊ��", Toast.LENGTH_LONG).show();
			}else{
			new Thread() {
	            @Override
	            public void run() { 
	            	getString getS=new getString();
	            	result=getS.doGet(connectURL);
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
class registListener implements android.view.View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, registActivity.class);
			MainActivity.this.startActivity(intent);			
		}
		
	} 
class ipListener implements android.view.View.OnClickListener {
	
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, ipAdd.class);
		MainActivity.this.startActivity(intent);			
	}
	
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

