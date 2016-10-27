package com.inspect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elevator.R;

public class addSucceed extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devicesucceed);
		Intent intent=getIntent();
		String deviceNo=intent.getStringExtra("deviceNo");
		System.out.println(deviceNo);
		setTitle("报检信息填写");
		TextView succeedView=(TextView)findViewById(R.id.information);
		succeedView.setText("恭喜您提交成功！\n此设备的设备编号为："+deviceNo);
		Button submitButton=(Button)findViewById(R.id.submit);
		submitButton.setText(R.string.MakeSure);
		submitButton.setOnClickListener(new submitListener());
	}
	class submitListener implements android.view.View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			intent.setClass(addSucceed.this, InspectActivity.class);
			addSucceed.this.startActivity(intent);
		
		} 
	}
}

