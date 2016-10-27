package com.check;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import com.adapter.localCheckAdapter;
import com.database.Shujuku;
import com.database.User;
import com.example.elevator.R;
import com.view.checkGoCHS;

//�ӱ������ݿ�������������أ�ѡ��һ��Ȼ����ת��checkNow���ѱ������ݿ��������ݴ��ݹ�ȥ
public class checkGoActivity extends Activity{
	TextView reportNoView;
	Button download;
	ListView lv;
	// ������ԣ�ֱ��д��public
	public HorizontalScrollView mTouchView;
	// װ�����е�HScrollView
	private List<checkGoCHS> mHScrollViews = new ArrayList<checkGoCHS>();
	Activity activity;
    private localCheckAdapter mAdapter;
	List<User> numberList;
	List<String> mission =new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_checkgo);
	    //���ȴ����ݿ��ж�ȡ����
	    getData();
		setTitle("����У��");
	    init_layout();
	    mAdapter = new localCheckAdapter(lv,mHScrollViews,numberList,checkGoActivity.this);  
		lv.setAdapter(mAdapter);
		mAdapter.notifyDataSetInvalidated();
		mAdapter.notifyDataSetChanged();
		//button ��ȡѡ�е���Ϣ�����浽�������ݿ���
		download.setOnClickListener(new OnClickListener(){  
            @Override  
            public void onClick(View v) {
            	int k=0;
            	for(int i=0;i<numberList.size();i++)
            	{
            		if(localCheckAdapter.isSelected.get(i)==true)
            		{
            			k=i;break;
            		}
            	}
            	User user=numberList.get(k);
            	Bundle bundle = new Bundle();
            	bundle.putSerializable("user", user);
            	Intent intent=new Intent();
            	intent.putExtra("bundle",bundle);
    			intent.setClass(checkGoActivity.this, checkNowActivity.class);
    			checkGoActivity.this.startActivity(intent);
            	
            }
	    
		});
	    
	  }
	private void getData()
	{
		Shujuku helper=new Shujuku(checkGoActivity.this);
		numberList=helper.findAll();
	}
	private void init_layout(){

		checkGoCHS headerScroll = (checkGoCHS) findViewById(R.id.item_scroll_title);
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
		phoneNoView.setText(R.string.PhoneNo);TextView deviceView=(TextView)findViewById(R.id.deviceNo);
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
		download.setText(R.string.CheckOneNow);

	}
		public void onScrollChanged(int l, int t, int oldl, int oldt) {
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			for (checkGoCHS scrollView : mHScrollViews) {
				// ��ֹ�ظ�����
				if (mTouchView != scrollView)
					scrollView.smoothScrollTo(l, t);
			}
		}

}


