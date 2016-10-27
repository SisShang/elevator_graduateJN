package com.inspect;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adapter.MyAdapter;
import com.check.CheckStartActivity2;
import com.database.Shujuku;
import com.database.User;
import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.example.elevator.R.id;
import com.example.elevator.R.layout;
import com.example.elevator.R.string;
import com.http.postParams;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class InspectActivity extends ActionBarActivity {
	String str2,str3,str4,str5=null;
	String connectURL,connectURL2=null;
	String ip,result=null;
	String succeed=null;
	private String type="buketuo";
	private RadioButton jianJinButton,buKeTuoButton,shunShiButton;
	private EditText addressEdit,unitEdit,contactEdit,phoneNoEdit,deviceNoEdit,governorSpecEdit;
	private EditText governorNoEdit,produceTimeEdit,produceUnitEdit,governorDiameterEdit,speedEdit;
	private RadioGroup roleGroup;
	private String deviceNo,governorSpec,governorNo,produceTime,produceUnit,governorDiameter,speed=null;
	User user;
	private int choose;
	JSONArray numberList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inspect);
		choose=0;
	    ipData helper=new ipData(InspectActivity.this);
		ipUser ipuser=helper.query(1);
		ip=ipuser.getNumber();	
		connectURL="http://"+ip+":8088/elevatorData/inspectadd.php";
		connectURL2="http://"+ip+":8088/elevatorData/inspectget.php";
		setTitle("������Ϣ��д");
		init_layout();
	
	}
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
					String result=(String)msg.obj;
					if(choose==1)//����豸
					{
						try {
							JSONObject jsonObject=new JSONObject(result);;
							succeed=jsonObject.getString("results");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//�жϷ���ֵ�Ƿ�Ϊtrue�����ǵĻ���������ҳ��
						if(succeed != null){
							Toast.makeText(InspectActivity.this, "�ύ�ɹ�", Toast.LENGTH_LONG).show();
							Intent intent=new Intent();
			            	intent.putExtra("deviceNo", succeed);
							intent.setClass(InspectActivity.this, addSucceed.class);
							InspectActivity.this.startActivity(intent);							
						}else{
						Toast.makeText(InspectActivity.this, "�ύʧ��", Toast.LENGTH_LONG).show();
						}
					
					}else if(choose==2){//�����豸
						try {
							JSONObject jsonObject=new JSONObject(result);
							//json��������Ϊresults
							numberList=jsonObject.getJSONArray("results");
							if(numberList.getJSONObject(0).getString("no").equals("no"))
							{
								Toast.makeText(InspectActivity.this, "û���ҵ���¼��\n��������ȷ��Ż�����¼�¼", Toast.LENGTH_LONG).show();
							}else{
								System.out.println("�����豸�ж�ִ��");
							user = new User("00",
									numberList.getJSONObject(0).getString("unit"),numberList.getJSONObject(0).getString("address"),
									numberList.getJSONObject(0).getString("contact"),numberList.getJSONObject(0).getString("phoneNo"),
									numberList.getJSONObject(0).getString("deviceNo"),
									numberList.getJSONObject(0).getString("governorSpec"),numberList.getJSONObject(0).getString("governorNo"),
									numberList.getJSONObject(0).getString("produceTime"),numberList.getJSONObject(0).getString("produceUnit"),
									numberList.getJSONObject(0).getString("diameter"),numberList.getJSONObject(0).getString("perimeter"),
									numberList.getJSONObject(0).getString("speed"),numberList.getJSONObject(0).getString("type"));
							
						Bundle bundle = new Bundle();
		            	bundle.putSerializable("user", user);
		            	Intent intent=new Intent();
		            	intent.putExtra("bundle",bundle);
		    			intent.setClass(InspectActivity.this, inspectGet.class);
		    			InspectActivity.this.startActivity(intent);
		    			} 
					}catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
			}
        	}
        
    };

	//�û���֪
	class readListener implements android.view.View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			intent.setClass(InspectActivity.this, userKnow.class);
			InspectActivity.this.startActivity(intent);
		}
		}
	//����豸
class submitListener implements android.view.View.OnClickListener {
	
	@Override
	public void onClick(View v) {
		choose=1;
		getEdit();
		if (TextUtils.isEmpty(str2)|| TextUtils.isEmpty(str3)|| TextUtils.isEmpty(str4)|| TextUtils.isEmpty(str5)) {  
            Toast.makeText(InspectActivity.this, "����д����", Toast.LENGTH_LONG).show();
		}else{
			new Thread() {
	            @Override
	            public void run() { 
	            	
	            	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	            	//ǰ���Ǳ�ǩ�������ֵ
	        		params.add(new BasicNameValuePair("address",str2.trim()));
	        		params.add(new BasicNameValuePair("unit",str3.trim()));
	        		params.add(new BasicNameValuePair("contact",str4.trim()));
	        		params.add(new BasicNameValuePair("phoneNo",str5.trim()));
	        		params.add(new BasicNameValuePair("governorNo",governorNo.trim()));
	        		params.add(new BasicNameValuePair("governorSpec",governorSpec.trim()));
	        		params.add(new BasicNameValuePair("produceTime",produceTime.trim()));
	        		params.add(new BasicNameValuePair("produceUnit",produceUnit.trim()));
	        		params.add(new BasicNameValuePair("governorDiameter",governorDiameter.trim()));
	        		params.add(new BasicNameValuePair("speed",speed.trim()));
	        		params.add(new BasicNameValuePair("type",type.trim()));
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
	void getEdit(){
		str2 = addressEdit.getText().toString();
		str3 = unitEdit.getText().toString();
		str4 = contactEdit.getText().toString();
		str5 = phoneNoEdit.getText().toString();
		governorSpec = governorSpecEdit.getText().toString();
		governorNo = governorNoEdit.getText().toString();
		produceTime = produceTimeEdit.getText().toString();
		produceUnit = produceUnitEdit.getText().toString();
		governorDiameter = governorDiameterEdit.getText().toString();
		speed = speedEdit.getText().toString();
	}
}
//�����豸
class getListener implements android.view.View.OnClickListener {
	
	@Override
	public void onClick(View v) {
		choose=2;
		deviceNo = deviceNoEdit.getText().toString();
			new Thread() {
	            @Override
	            public void run() { 
	            	
	            	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	            	//ǰ���Ǳ�ǩ�������ֵ
	        		params.add(new BasicNameValuePair("deviceNo",deviceNo.trim()));
	            	postParams getS=new postParams();
	            	result=getS.dePost(connectURL2, params);
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

private void init_layout(){
	TextView informationView=(TextView)findViewById(R.id.information);
	informationView.setText(R.string.Information);
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
	TextView speedView=(TextView)findViewById(R.id.speed);
	speedView.setText(R.string.Speed);
	roleGroup=(RadioGroup)findViewById(R.id.safeType);
	jianJinButton=(RadioButton)findViewById(R.id.jianJin);
	jianJinButton.setText(R.string.JianJin);
	buKeTuoButton=(RadioButton)findViewById(R.id.buKeTuo);
	buKeTuoButton.setText(R.string.BuKeTuo);
	shunShiButton=(RadioButton)findViewById(R.id.shunShi);
	shunShiButton.setText(R.string.ShunShi);
	roleGroup.setOnCheckedChangeListener(mChangeRadio);
	Button submitButton=(Button)findViewById(R.id.submit);
	submitButton.setText(R.string.Add);
	submitButton.setOnClickListener(new submitListener());
	Button readButton=(Button)findViewById(R.id.read);
	readButton.setText(R.string.Read);
	readButton.setTextColor(Color.parseColor("#3366cc"));
	readButton.setOnClickListener(new readListener());
	Button getButton=(Button)findViewById(R.id.get);
	getButton.setText(R.string.Get);
	getButton.setOnClickListener(new getListener());
	addressEdit = (EditText)findViewById(R.id.addressIn);
	unitEdit = (EditText)findViewById(R.id.unitIn);
	contactEdit = (EditText)findViewById(R.id.contactIn);
	phoneNoEdit = (EditText)findViewById(R.id.phoneNoIn);
	deviceNoEdit = (EditText)findViewById(R.id.deviceNoIn);
//	deviceNoEdit.setTextColor(Color.parseColor("#a4d3ee"));
	governorSpecEdit = (EditText)findViewById(R.id.governorSpecIn);
	governorNoEdit = (EditText)findViewById(R.id.governorNoIn);
	produceTimeEdit = (EditText)findViewById(R.id.produceTimeIn);
	produceUnitEdit = (EditText)findViewById(R.id.produceUnitIn);
	governorDiameterEdit = (EditText)findViewById(R.id.governorDiameterIn);
	speedEdit = (EditText)findViewById(R.id.speedIn);

}
private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener() {
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if (checkedId == jianJinButton.getId()) {
			// ��mRadio1�����ݴ���mTextView1
			type ="����ʽ";
		} else if (checkedId == buKeTuoButton.getId()) {
			// ��mRadio2�����ݴ���mTextView1
			type ="������ʽ";
		}else if(checkedId == shunShiButton.getId()) {
			// ��mRadio2�����ݴ���mTextView1
			type ="˲ʱʽ";
		}
	}
};
}
