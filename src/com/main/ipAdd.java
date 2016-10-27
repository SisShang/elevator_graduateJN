package com.main;

import com.database.ipData;
import com.database.ipUser;
import com.example.elevator.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ipAdd extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ipadd);
		TextView numberView=(TextView)findViewById(R.id.numberview);
		numberView.setText("ip:");
		Button numberButton=(Button)findViewById(R.id.numberbutton);
		numberButton.setText(R.string.Submit);
		numberButton.setOnClickListener(new firstButtonListener());
	}

	class firstButtonListener implements android.view.View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			

			final EditText edit = (EditText)findViewById(R.id.numbertext);
			String str = edit.getText().toString();
			ipUser user=new ipUser(str);
			ipData helper=new ipData(ipAdd.this);
			helper.update(user);		
			Intent intent=new Intent();
			intent.setClass(ipAdd.this, MainActivity.class);
			ipAdd.this.startActivity(intent);
		}
		
		
	} 

}
