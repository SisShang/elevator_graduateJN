package com.check;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adapter.MyAdapter;
import com.http.postParams;
import com.database.Shujuku;
import com.database.User;
import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;
import com.view.CHScrollView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class missionReceiveActivity extends Activity {
	String connectURL=null;
	String ip,isSucceed=null;
	String result=null;
	ArrayList<String> choose=new ArrayList<String>();
	TextView reportNoView;
	Button download;
	ListView lv;
	// 方便测试，直接写的public
	public HorizontalScrollView mTouchView;
	// 装入所有的HScrollView
	private List<CHScrollView> mHScrollViews = new ArrayList<CHScrollView>();
	Activity activity;
    private MyAdapter mAdapter;
	JSONArray numberList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_mission_receive);  
	    ipData helper=new ipData(missionReceiveActivity.this);
		ipUser ipuser=helper.query(1);
		ip=ipuser.getNumber();	
	    connectURL="http://"+ip+":8088/elevatorData/query.php";
	    init_layout();
	    thread.start();
	    
	    
	  }
	
	private void init_layout(){

		CHScrollView headerScroll = (CHScrollView) findViewById(R.id.item_scroll_title);
		// 添加头滑动事件
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
		download=(Button)findViewById(R.id.download);
		download.setText(R.string.Download);
		System.out.println("ok1");

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
						numberList=jsonObject.getJSONArray("results");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						mAdapter = new MyAdapter(lv,mHScrollViews,numberList,missionReceiveActivity.this);  
						lv.setAdapter(mAdapter);
						mAdapter.notifyDataSetInvalidated();
						mAdapter.notifyDataSetChanged();
						//button 获取选中的信息并保存到本地数据库中
						download.setOnClickListener(new OnClickListener(){  
				            @Override  
				            public void onClick(View v) {
				            	choose.clear();
				            	for(int i=0;i<numberList.length();i++){  
				            		//如果信息选中，将所有信息保存到本地数据库，并将isReceive=1发送给远程数据库
				                    if(MyAdapter.isSelected.get(i)==true){  
				                    	User user;
										try {
											user = new User(numberList.getJSONObject(i).getString("reportNo"),
													numberList.getJSONObject(i).getString("unit"),numberList.getJSONObject(i).getString("address"),
													numberList.getJSONObject(i).getString("contact"),numberList.getJSONObject(i).getString("phoneNo"),
													numberList.getJSONObject(i).getString("deviceNo"),
													numberList.getJSONObject(i).getString("governorSpec"),numberList.getJSONObject(i).getString("governorNo"),
													numberList.getJSONObject(i).getString("produceTime"),numberList.getJSONObject(i).getString("produceUnit"),
													numberList.getJSONObject(i).getString("diameter"),numberList.getJSONObject(i).getString("perimeter"),
													numberList.getJSONObject(i).getString("speed"),numberList.getJSONObject(i).getString("type"));
				            			Shujuku helper=new Shujuku(missionReceiveActivity.this);
				            			helper=new Shujuku(missionReceiveActivity.this);
				            			if(helper.query(numberList.getJSONObject(i).getString("reportNo").trim())==null)
				            				helper.insert(user);
				            			else
				            				helper.update(user);
				            			choose.add(numberList.getJSONObject(i).getString("reportNo").trim());
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
				                    }
				                }   
				            	Intent intent=new Intent();
				            	intent.putStringArrayListExtra("chooseNo", choose);
				    			intent.setClass(missionReceiveActivity.this, downloadSucceed.class);
				    			missionReceiveActivity.this.startActivity(intent);
				            }  
				              
				        });  
					
					break;
				}
				
			}
		};
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        	//前边是标签，后边是值,知识发送过去一个数，为了使php获取网址
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
			for (CHScrollView scrollView : mHScrollViews) {
				// 防止重复滑动
				if (mTouchView != scrollView)
					scrollView.smoothScrollTo(l, t);
			}
		}

}

