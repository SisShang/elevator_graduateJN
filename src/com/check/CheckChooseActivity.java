package com.check;

import com.database.Shujuku;
import com.example.elevator.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class CheckChooseActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_choose);
		setTitle("µçÌÝÐ£Ñé");
		Button missionReceiveButton=(Button)findViewById(R.id.missionReceive);
		missionReceiveButton.setText(R.string.MissionReceive);
		missionReceiveButton.setOnClickListener(new missionReceiveListener());
		Button checkNowButton=(Button)findViewById(R.id.checkNow);
		checkNowButton.setText(R.string.CheckNow);
		checkNowButton.setOnClickListener(new checkNowListener());
	}
	class missionReceiveListener implements android.view.View.OnClickListener {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		//	Shujuku helper=new Shujuku(CheckChooseActivity.this);
		//	helper.deleteDatabase(CheckChooseActivity.this);
			Intent intent=new Intent();
			intent.setClass(CheckChooseActivity.this, missionReceiveActivity.class);
			CheckChooseActivity.this.startActivity(intent);
			}
	}
	class checkNowListener implements android.view.View.OnClickListener {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(CheckChooseActivity.this, checkGoActivity.class);
			CheckChooseActivity.this.startActivity(intent);
		}
	}
}