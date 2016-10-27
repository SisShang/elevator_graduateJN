package com.main;

import com.check.CheckChooseActivity;
import com.example.elevator.R;
import com.inspect.InspectActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class deal extends Activity{
	String which=null;
	private TextView succeedView;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deal);
		Intent i=getIntent();		
		which=i.getStringExtra("ok");
		System.out.println(which);
		succeedView=(TextView)findViewById(R.id.succeed);
		Button submitButton=(Button)findViewById(R.id.submit);
		submitButton.setText(R.string.MakeSure);
		submitButton.setOnClickListener(new submitListener());
	}
	public class submitListener implements android.view.View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			if(which.equals("inspect"))
			{
				Intent intent=new Intent();
				intent.setClass(deal.this, InspectActivity.class);
				deal.this.startActivity(intent);
			}else if(which.equals("regist"))
			{
				succeedView.setText(R.string.succ);
				Intent intent=new Intent();
				intent.setClass(deal.this, MainActivity.class);
				deal.this.startActivity(intent);
			}else if(which.equals("check"))
			{
				Intent intent=new Intent();
				intent.setClass(deal.this, CheckChooseActivity.class);
				deal.this.startActivity(intent);
			}
		
		} 
	}

}
