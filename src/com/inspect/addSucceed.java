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
		setTitle("������Ϣ��д");
		TextView succeedView=(TextView)findViewById(R.id.information);
		succeedView.setText("��ϲ���ύ�ɹ���\n���豸���豸���Ϊ��"+deviceNo);
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

