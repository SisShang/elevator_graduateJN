package com.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adapter.auditAdapter;
import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.http.postParams;
import com.main.MainActivity;
import com.view.auditCHS;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AuditActivity extends Activity {
	String connectURL=null;
	String ip,isSucceed=null;
	String result=null;
	ArrayList<String> choose=new ArrayList<String>();
	TextView reportNoView;
	Button sendSucceed,sendFailed;
	ListView lv;
	// ������ԣ�ֱ��д��public
	public HorizontalScrollView mTouchView;
	// װ�����е�HScrollView
	private List<auditCHS> mHScrollViews = new ArrayList<auditCHS>();
	Activity activity;
    private auditAdapter mAdapter;
	JSONArray numberList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_report_receive);
	    ipData helper=new ipData(AuditActivity.this);
		ipUser user=helper.query(1);
		ip=user.getNumber();		
		connectURL="http://"+ip+":8088/elevatorData/audit.php";
		setTitle("������Ϣ���");
	    init_layout();
	    thread.start();
	    
	    
	  }
	
	private void init_layout(){

		auditCHS headerScroll = (auditCHS) findViewById(R.id.item_scroll_title);
		// ���ͷ�����¼�
		mHScrollViews.add(headerScroll);
		lv=(ListView)findViewById(R.id.listView);
		reportNoView=(TextView)findViewById(R.id.reportNo);
		reportNoView.setText(R.string.ReportNo);
		TextView unitView=(TextView)findViewById(R.id.unit);
		unitView.setText(R.string.Unit);
		TextView addressView=(TextView)findViewById(R.id.address);
		addressView.setText(R.string.Address);
		TextView contactView=(TextView)findViewById(R.id.contact);
		contactView.setText(R.string.Contact);
		TextView phoneNoView=(TextView)findViewById(R.id.phoneNo);
		phoneNoView.setText(R.string.PhoneNo);
		TextView deviceView=(TextView)findViewById(R.id.deviceNo);
		deviceView.setText(R.string.Unit);
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
		TextView carSpeedTimeView=(TextView)findViewById(R.id.carSpeedTime);
		carSpeedTimeView.setText(R.string.CarSpeedTime);
		TextView carSpeed1View=(TextView)findViewById(R.id.carSpeed1);
		carSpeed1View.setText(R.string.CarSpeed1);
		TextView carSpeed2View=(TextView)findViewById(R.id.carSpeed2);
		carSpeed2View.setText(R.string.CarSpeed2);
		TextView carSpeed3View=(TextView)findViewById(R.id.carSpeed3);
		carSpeed3View.setText(R.string.CarSpeed3);
		TextView carSpeedAveView=(TextView)findViewById(R.id.carSpeedAve);
		carSpeedAveView.setText(R.string.CarSpeedAve);
		TextView counterSpeedTimeView=(TextView)findViewById(R.id.counterSpeedTime);
		counterSpeedTimeView.setText(R.string.CounterSpeedTime);
		TextView counterSpeed1View=(TextView)findViewById(R.id.counterSpeed1);
		counterSpeed1View.setText(R.string.CounterSpeed1);
		TextView counterSpeed2View=(TextView)findViewById(R.id.counterSpeed2);
		counterSpeed2View.setText(R.string.CounterSpeed2);
		TextView counterSpeed3View=(TextView)findViewById(R.id.counterSpeed3);
		counterSpeed3View.setText(R.string.CounterSpeed3);
		TextView counterSpeedAveView=(TextView)findViewById(R.id.counterSpeedAve);
		counterSpeedAveView.setText(R.string.CounterSpeedAve);
		TextView overSpeedTimeView=(TextView)findViewById(R.id.overSpeedTime);
		overSpeedTimeView.setText(R.string.OverSpeedTime);
		TextView overSpeed1View=(TextView)findViewById(R.id.overSpeed1);
		overSpeed1View.setText(R.string.OverSpeed1);
		TextView overSpeed2View=(TextView)findViewById(R.id.overSpeed2);
		overSpeed2View.setText(R.string.OverSpeed2);
		TextView overSpeed3View=(TextView)findViewById(R.id.overSpeed3);
		overSpeed3View.setText(R.string.OverSpeed3);
		TextView overSpeedAveView=(TextView)findViewById(R.id.overSpeedAve);
		overSpeedAveView.setText(R.string.OverSpeedAve);
		
		
		sendSucceed=(Button)findViewById(R.id.sendSucceed);
		sendSucceed.setText(R.string.SendSucceed);
		sendFailed=(Button)findViewById(R.id.sendFailed);
		sendFailed.setText(R.string.SendFailed);

	}
	
	public class SMSReceiver extends BroadcastReceiver { 
		    @Override  
		    public void onReceive(Context context, Intent intent) {
		    	final String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION"; 
		    	if(intent.getAction().equals(DELIVERED_SMS_ACTION))
		    	{isSucceed="succeed";}
		    	else
		    	{isSucceed="failed";
		    	}
		    }  				
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
					try {
						JSONObject jsonObject=new JSONObject(result);
						//json��������Ϊresults
						numberList=jsonObject.getJSONArray("results");
						mAdapter = new auditAdapter(lv,mHScrollViews,numberList,AuditActivity.this);  
						lv.setAdapter(mAdapter);
						mAdapter.notifyDataSetInvalidated();
						mAdapter.notifyDataSetChanged();
						//button ��ȡѡ�е���Ϣ�����浽�������ݿ���
						sendSucceed.setOnClickListener(new sendSucceedListener()); 
						sendFailed.setOnClickListener(new sendFailedListener()); 
				            
				          
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					break;
				}
				
			}
			
		};
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        	//ǰ���Ǳ�ǩ�������ֵ,֪ʶ���͹�ȥһ������Ϊ��ʹphp��ȡ��ַ
    		params.add(new BasicNameValuePair("send","1"));
    		postParams getS=new postParams();
        	result=getS.dePost(connectURL, params);
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
		
	
		public void onScrollChanged(int l, int t, int oldl, int oldt) {
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			for (auditCHS scrollView : mHScrollViews) {
				// ��ֹ�ظ�����
				if (mTouchView != scrollView)
					scrollView.smoothScrollTo(l, t);
			}
		}
		public void sendMessage(String strno,int i){
			// TODO Auto-generated method stub
			//������
			SmsManager smsManager = SmsManager.getDefault();  
		    final String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";  
		 // create the deilverIntent parameter  
		 Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);  
		 PendingIntent deliverPI = PendingIntent.getBroadcast(AuditActivity.this, 0,deliverIntent, 0);
		 if(i==1)
		 {
			    smsManager.sendTextMessage(strno, null, "���ã�����ҵ����У����ϣ�У����Ϊ�ϸ��븶��", null, deliverPI);
		 }
		 else if(i==0)
		 {
			 smsManager.sendTextMessage(strno, null, "���ã�����ҵ����У����ϣ����ź�У����Ϊ���ϸ�", null, deliverPI);
		 }
				SMSReceiver smsreceiver=new SMSReceiver();
				smsreceiver.onReceive(getBaseContext(), deliverIntent);
		}
		class sendSucceedListener implements android.view.View.OnClickListener { 
            @Override  
            public void onClick(View v) {
            	choose.clear();
            	for(int i=0;i<numberList.length();i++){  
            		//�����Ϣѡ�У���������Ϣ���浽�������ݿ⣬����isReceive=1���͸����ݿ�
                    if(auditAdapter.isSelected.get(i)==true){  
            			try {
							choose.add(numberList.getJSONObject(i).getString("reportNo").trim());
							sendMessage(numberList.getJSONObject(i).getString("phoneNo").trim(),1);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }   
            	if(isSucceed.equals("succeed"))
            	{
            		choose.add("1");
	            	Intent intent=new Intent();
	            	intent.putStringArrayListExtra("chooseNo", choose);
	    			intent.setClass(AuditActivity.this, auditSucceed.class);
	    			AuditActivity.this.startActivity(intent);
            	}
            	else
            		Toast.makeText(getApplicationContext(),"���ŷ���ʧ��", Toast.LENGTH_SHORT).show();
            }  
              
        } 
		class sendFailedListener implements android.view.View.OnClickListener { 
			@Override  
            public void onClick(View v) {
            	choose.clear();
            	for(int i=0;i<numberList.length();i++){  
            		//�����Ϣѡ�У���������Ϣ���浽�������ݿ⣬����isReceive=1���͸����ݿ�
                    if(auditAdapter.isSelected.get(i)==true){  
						try {
							choose.add(numberList.getJSONObject(i).getString("reportNo").trim());
							sendMessage(numberList.getJSONObject(i).getString("phoneNo").trim(),0);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }
            	if(isSucceed.equals("succeed"))
            	{
            		choose.add("0") ;
	            	Intent intent=new Intent();
	            	intent.putStringArrayListExtra("chooseNo", choose);
	    			intent.setClass(AuditActivity.this, auditSucceed.class);
	    			AuditActivity.this.startActivity(intent);
            	}
            	else
            		Toast.makeText(getApplicationContext(),"���ŷ���ʧ��", Toast.LENGTH_SHORT).show();
            
            }  
              
			
		}

}

