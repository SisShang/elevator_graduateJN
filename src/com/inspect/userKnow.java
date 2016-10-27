package com.inspect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elevator.R;

public class userKnow extends Activity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userknow);
		TextView succeedView=(TextView)findViewById(R.id.userKnowview);
		succeedView.setText(R.string.UserKnow);
		Button submitButton=(Button)findViewById(R.id.sure);
		submitButton.setText(R.string.MakeSure);
		submitButton.setOnClickListener(new submitListener());
	}
	class submitListener implements android.view.View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			intent.setClass(userKnow.this, InspectActivity.class);
			userKnow.this.startActivity(intent);
		
		} 
	}

}

