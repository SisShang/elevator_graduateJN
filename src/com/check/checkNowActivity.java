package com.check;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.audit.AuditActivity;
import com.database.Shujuku;
import com.database.User;
import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.http.postParams;


//第185行
public class checkNowActivity extends ActionBarActivity {
	
	String reportNo,deviceNo,governorSpec,governorNo,produceTime,produceUnit,governorDiameter,
		governorPerimeter,speed,checkName,deviceName=null;
	String connectURL=null;
	String isSucceed=null;
	String ip,result=null;
	String succeed=null;
	private String type="buketuo";
	private User user;
	private EditText reportNoEdit,addressEdit,unitEdit,contactEdit,phoneNoEdit,deviceNoEdit,checkNameEdit,deviceNameEdit;
	private EditText governorSpecEdit,governorNoEdit,produceTimeEdit,produceUnitEdit,governorDiameterEdit,governorPerimeterEdit,speedEdit;
	private RadioButton jianJinButton,buKeTuoButton,shunShiButton;
	private RadioGroup roleGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_now);
	    ipData helper=new ipData(checkNowActivity.this);
		ipUser ipuser=helper.query(1);
		ip=ipuser.getNumber();	
		connectURL="http://"+ip+":8088/elevatorData/checkMission.php";
		Intent i=getIntent();
		Bundle bundle=i.getBundleExtra("bundle");
		user=(User) bundle.getSerializable("user");
		setTitle("基本信息核对");
		init_layout();	
	}
	private void init_layout(){
		TextView informationView=(TextView)findViewById(R.id.information);
		informationView.setText(R.string.Information);
		TextView reportNoView=(TextView)findViewById(R.id.reportNo);
		reportNoView.setText(R.string.ReportNo);
		TextView checkNameView=(TextView)findViewById(R.id.checkName);
		checkNameView.setText(R.string.CheckName);
		TextView deviceNameView=(TextView)findViewById(R.id.deviceName);
		deviceNameView.setText(R.string.DeviceName);
		TextView unitView=(TextView)findViewById(R.id.unit);
		unitView.setText(R.string.Unit);
		TextView addressView=(TextView)findViewById(R.id.address);
		addressView.setText(R.string.Address);
		TextView contactView=(TextView)findViewById(R.id.contact);
		contactView.setText(R.string.Contact);
		TextView phoneNoView=(TextView)findViewById(R.id.phoneNo);
		phoneNoView.setText(R.string.PhoneNo);
		TextView deviceNoView=(TextView)findViewById(R.id.deviceNo);
		deviceNoView.setText(R.string.DeviceNo);
		TextView governorSpecView=(TextView)findViewById(R.id.governorSpec);
		governorSpecView.setText(R.string.GovernorSpec);
		TextView governorNoView=(TextView)findViewById(R.id.governorNo);
		governorNoView.setText(R.string.GovernorNo);
		TextView produceTimeView=(TextView)findViewById(R.id.produceTime);
		produceTimeView.setText(R.string.ProduceTime);
		TextView produceUnitView=(TextView)findViewById(R.id.produceUnit);
		produceUnitView.setText(R.string.ProduceUnit);
		TextView governorDiameterView=(TextView)findViewById(R.id.governorDiameter);
		governorDiameterView.setText(R.string.GovernorDiameter);
		TextView governorPerimeterView=(TextView)findViewById(R.id.governorPerimeter);
		governorPerimeterView.setText(R.string.GovernorPerimeter);
		TextView speedView=(TextView)findViewById(R.id.speed);
		speedView.setText(R.string.Speed);
		checkNameEdit= (EditText)findViewById(R.id.checkNameIn);
		deviceNameEdit= (EditText)findViewById(R.id.deviceNameIn);
		reportNoEdit = (EditText)findViewById(R.id.reportNoIn);
		reportNoEdit.setTextColor(Color.parseColor("#8B4513"));
		reportNoEdit.setText(user.getReportNo().trim());
		reportNoEdit.setEnabled(false);
		reportNo = user.getReportNo();
		addressEdit = (EditText)findViewById(R.id.addressIn);
		addressEdit.setTextColor(Color.parseColor("#8B4513"));
		addressEdit.setText(user.getAddress().trim());
		addressEdit.setEnabled(false);
		unitEdit = (EditText)findViewById(R.id.unitIn);
		unitEdit.setTextColor(Color.parseColor("#8B4513"));
		unitEdit.setText(user.getUnit().trim());
		unitEdit.setEnabled(false);
		contactEdit = (EditText)findViewById(R.id.contactIn);
		contactEdit.setTextColor(Color.parseColor("#8B4513"));
		contactEdit.setText(user.getContact().trim());
		contactEdit.setEnabled(false);
		phoneNoEdit = (EditText)findViewById(R.id.phoneNoIn);
		phoneNoEdit.setTextColor(Color.parseColor("#8B4513"));
		phoneNoEdit.setText(user.getPhoneNo().trim());
		phoneNoEdit.setEnabled(false);
		deviceNoEdit = (EditText)findViewById(R.id.deviceNoIn);
		deviceNoEdit.setTextColor(Color.parseColor("#8B4513"));
		deviceNoEdit.setText(user.getDeviceNo().trim());
		deviceNoEdit.setEnabled(true);
		governorSpecEdit = (EditText)findViewById(R.id.governorSpecIn);
		governorSpecEdit.setTextColor(Color.parseColor("#8B4513"));
		governorSpecEdit.setText(user.getGovernorSpec().trim());
		governorSpecEdit.setEnabled(true);
		governorNoEdit = (EditText)findViewById(R.id.governorNoIn);
		governorNoEdit.setTextColor(Color.parseColor("#8B4513"));
		governorNoEdit.setText(user.getGovernorNo().trim());
		governorNoEdit.setEnabled(true);
		produceTimeEdit = (EditText)findViewById(R.id.produceTimeIn);
		produceTimeEdit.setTextColor(Color.parseColor("#8B4513"));
		produceTimeEdit.setText(user.getProduceTime().trim());
		produceTimeEdit.setEnabled(true);
		produceUnitEdit = (EditText)findViewById(R.id.produceUnitIn);
		produceUnitEdit.setTextColor(Color.parseColor("#8B4513"));
		produceUnitEdit.setText(user.getProduceUnit().trim());
		produceUnitEdit.setEnabled(true);
		governorDiameterEdit = (EditText)findViewById(R.id.governorDiameterIn);
		governorDiameterEdit.setTextColor(Color.parseColor("#8B4513"));
		governorDiameterEdit.setText(user.getGovernorDiameter().trim());
		governorDiameterEdit.setEnabled(true);
		governorPerimeterEdit = (EditText)findViewById(R.id.governorPerimeterIn);
		governorPerimeterEdit.setTextColor(Color.parseColor("#8B4513"));
		governorPerimeterEdit.setText(user.getGovernorPerimeter().trim());
		governorPerimeterEdit.setEnabled(true);
		speedEdit = (EditText)findViewById(R.id.speedIn);
		speedEdit.setTextColor(Color.parseColor("#8B4513"));
		speedEdit.setText(user.getSpeed().trim());
		speedEdit.setEnabled(true);
		roleGroup=(RadioGroup)findViewById(R.id.safeType);
		jianJinButton=(RadioButton)findViewById(R.id.jianJin);
		jianJinButton.setText(R.string.JianJin);
		buKeTuoButton=(RadioButton)findViewById(R.id.buKeTuo);
		buKeTuoButton.setText(R.string.BuKeTuo);
		shunShiButton=(RadioButton)findViewById(R.id.shunShi);
		shunShiButton.setText(R.string.ShunShi);
		if(user.getType().trim().equals("渐进式"))
			jianJinButton.setChecked(true);
		else if(user.getType().trim().equals("不可脱式"))
			buKeTuoButton.setChecked(true);
		else if(user.getType().trim().equals("瞬时式"))
			shunShiButton.setChecked(true);
		roleGroup.setOnCheckedChangeListener(mChangeRadio);
		
		Button submitButton=(Button)findViewById(R.id.submit);
		submitButton.setText(R.string.Submit);
		submitButton.setOnClickListener(new submitListener());
		
	}
	private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == jianJinButton.getId()) {
				// 把mRadio1的内容传到mTextView1
				type ="渐进式";
			} else if (checkedId == buKeTuoButton.getId()) {
				// 把mRadio2的内容传到mTextView1
				type ="不可脱式";
			}else if(checkedId == shunShiButton.getId()) {
				// 把mRadio2的内容传到mTextView1
				type ="瞬时式";
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
					String result=(String)msg.obj;
					JSONObject jsonObject;
					try {
						jsonObject = new JSONObject(result);
						succeed=jsonObject.getString("results");
						System.out.println(succeed);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if("succeed".equals(succeed))
		        	{
						Shujuku helper=new Shujuku(checkNowActivity.this);
            			helper.delete(user.getReportNo());
						Toast.makeText(checkNowActivity.this, "提交成功", Toast.LENGTH_LONG).show();
					
						Intent intent=new Intent();
						intent.setClass(checkNowActivity.this, CheckStartActivity1.class);
						intent.putExtra("reportNo", reportNo);
						checkNowActivity.this.startActivity(intent);
					
					}else{
						Toast.makeText(checkNowActivity.this, "提交失败", Toast.LENGTH_LONG).show();
					}
					
		        }
        }
        
    };
class submitListener implements android.view.View.OnClickListener {
	
	@Override
	public void onClick(View v) {
		getEdit();
		
		if (TextUtils.isEmpty(reportNo)|| TextUtils.isEmpty(deviceName)||TextUtils.isEmpty(checkName)) {  
            Toast.makeText(checkNowActivity.this, "请填写完整", Toast.LENGTH_LONG).show();
		}else{
			new Thread() {
	            @Override
	            public void run() { 
	            	/**************************************************************8
	            	 * 此处需要改的地方，不能发汉字，type无法获取
	            	 *************************************************************8*/
	            	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	        		params.add(new BasicNameValuePair("reportNo",reportNo.trim()));
	        		params.add(new BasicNameValuePair("checkName",deviceNo.trim()));
	        		params.add(new BasicNameValuePair("deviceName",governorSpec.trim()));
	        		params.add(new BasicNameValuePair("deviceNo",deviceNo.trim()));
	        		params.add(new BasicNameValuePair("governorSpec",governorSpec.trim()));
	        		params.add(new BasicNameValuePair("produceTime",produceTime.trim()));
	        		params.add(new BasicNameValuePair("produceUnit",produceUnit.trim()));
	        		params.add(new BasicNameValuePair("governorDiameter",governorDiameter.trim()));
	        		params.add(new BasicNameValuePair("governorPerimeter",governorPerimeter.trim()));
	        		params.add(new BasicNameValuePair("speed",speed.trim()));
	        		params.add(new BasicNameValuePair("type",type.trim()));
	        		postParams getS=new postParams();
	        		System.out.println(params);
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
	deviceNo = deviceNoEdit.getText().toString();
	governorSpec = governorSpecEdit.getText().toString();
	checkName = deviceNoEdit.getText().toString();
	deviceName = governorSpecEdit.getText().toString();
	governorNo = governorNoEdit.getText().toString();
	governorDiameter = governorDiameterEdit.getText().toString();
	produceTime = produceTimeEdit.getText().toString();
	produceUnit = produceUnitEdit.getText().toString();
	governorPerimeter = governorPerimeterEdit.getText().toString();
	speed = speedEdit.getText().toString();

} 
}
