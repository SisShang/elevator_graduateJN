package com.audit;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.database.User;
import com.example.elevator.R;

public class reportSee extends Activity {
	private JSONObject list;
	private TextView report, reportNo, reportNoIn, address, addressIn, contact,
			contactIn;
	private TextView phoneNo, phoneNoIn, governorSpec, governorSpecIn,
			governorNo, governorNoIn;
	private TextView produceTime, produceTimeIn, produceUnit, produceUnitIn,
			speed, speedIn, type, typeIn;
	private TextView yiJu, guiFanIn, deviceName, deviceNameIn, result,
			resultIn;
	private TextView beiZhu, beiZhuIn, checkName, checkNameIn, bianZhi,
			bianZhiIn, riQi1, riQiIn1;
	private TextView shenHe, shenHeIn, riQi2, riQiIn2, piZhun, piZhunIn, riQi3,
			riQiIn3;
	private TextView xiangMu, jieGuo, jieLun, car, jieGuo1, jieLun1, counter,
			jieGuo2, jieLun2;
	private TextView over, jieGuo3, jieLun3;
	private SimpleDateFormat formatter;
	private Date curDate;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportsee);
		Intent i = getIntent();
		try {
			list = new JSONObject(i.getStringExtra("list"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init_layout();
	}

	private void init_layout() {
		try {
			report = (TextView) findViewById(R.id.report);
			reportNo = (TextView) findViewById(R.id.reportNo);
			reportNoIn = (TextView) findViewById(R.id.reportNoIn);
			address = (TextView) findViewById(R.id.address);
			addressIn = (TextView) findViewById(R.id.addressIn);
			addressIn.setGravity(Gravity.CENTER);
			contact = (TextView) findViewById(R.id.contact);
			contactIn = (TextView) findViewById(R.id.contactIn);
			contactIn.setGravity(Gravity.CENTER);
			phoneNo = (TextView) findViewById(R.id.phoneNo);
			phoneNoIn = (TextView) findViewById(R.id.phoneNoIn);
			phoneNoIn.setGravity(Gravity.CENTER);
			governorSpec = (TextView) findViewById(R.id.governorSpec);
			governorSpecIn = (TextView) findViewById(R.id.governorSpecIn);
			governorSpecIn.setGravity(Gravity.CENTER);
			governorNo = (TextView) findViewById(R.id.governorNo);
			governorNoIn = (TextView) findViewById(R.id.governorNoIn);
			governorNoIn.setGravity(Gravity.CENTER);
			produceTime = (TextView) findViewById(R.id.produceTime);
			produceTimeIn = (TextView) findViewById(R.id.produceTimeIn);
			produceTimeIn.setGravity(Gravity.CENTER);
			produceUnit = (TextView) findViewById(R.id.produceUnit);
			produceUnitIn = (TextView) findViewById(R.id.produceUnitIn);
			produceUnitIn.setGravity(Gravity.CENTER);
			speed = (TextView) findViewById(R.id.speed);
			speedIn = (TextView) findViewById(R.id.speedIn);
			speedIn.setGravity(Gravity.CENTER);
			type = (TextView) findViewById(R.id.type);
			typeIn = (TextView) findViewById(R.id.typeIn);
			yiJu = (TextView) findViewById(R.id.yiJu);
			guiFanIn = (TextView) findViewById(R.id.guiFanIn);
			deviceName = (TextView) findViewById(R.id.deviceName);
			deviceNameIn = (TextView) findViewById(R.id.deviceNameIn);
			result = (TextView) findViewById(R.id.result);
			resultIn = (TextView) findViewById(R.id.resultIn);
			beiZhu = (TextView) findViewById(R.id.beiZhu);
			beiZhuIn = (TextView) findViewById(R.id.beiZhuIn);
			checkName = (TextView) findViewById(R.id.checkName);
			checkNameIn = (TextView) findViewById(R.id.checkNameIn);
			bianZhi = (TextView) findViewById(R.id.bianZhi);
			bianZhiIn = (TextView) findViewById(R.id.bianZhiIn);
			riQi1 = (TextView) findViewById(R.id.riQi1);
			riQiIn1 = (TextView) findViewById(R.id.riQiIn1);
			shenHe = (TextView) findViewById(R.id.shenHe);
			shenHeIn = (TextView) findViewById(R.id.shenHeIn);
			riQi2 = (TextView) findViewById(R.id.riQi2);
			riQiIn2 = (TextView) findViewById(R.id.riQiIn2);
			piZhun = (TextView) findViewById(R.id.piZhun);
			piZhunIn = (TextView) findViewById(R.id.piZhunIn);
			riQi3 = (TextView) findViewById(R.id.riQi3);
			riQiIn3 = (TextView) findViewById(R.id.riQiIn3);
			xiangMu = (TextView) findViewById(R.id.xiangMu);
			jieGuo = (TextView) findViewById(R.id.jieGuo);
			jieLun = (TextView) findViewById(R.id.jieLun);
			car = (TextView) findViewById(R.id.car);
			jieGuo1 = (TextView) findViewById(R.id.jieGuo1);
			jieLun1 = (TextView) findViewById(R.id.jieLun1);
			counter = (TextView) findViewById(R.id.counter);
			jieGuo2 = (TextView) findViewById(R.id.jieGuo2);
			jieLun2 = (TextView) findViewById(R.id.jieLun2);
			over = (TextView) findViewById(R.id.over);
			jieGuo3 = (TextView) findViewById(R.id.jieGuo3);
			jieLun3 = (TextView) findViewById(R.id.jieLun3);
////////////////////////////////////////////////////////////////
			reportNoIn.setText(list.getString("reportNo"));
			addressIn.setText(list.getString("address"));
			addressIn.setGravity(Gravity.CENTER);
			contactIn.setText(list.getString("contact"));
			contactIn.setGravity(Gravity.CENTER);
			phoneNoIn.setText(list.getString("phoneNo"));
			phoneNoIn.setGravity(Gravity.CENTER);
			governorSpecIn.setText(list.getString("governorSpec"));
			governorSpecIn.setGravity(Gravity.CENTER);
			governorNoIn.setText(list.getString("governorNo"));
			governorNoIn.setGravity(Gravity.CENTER);
			produceTimeIn.setText(list.getString("produceTime"));
			produceTimeIn.setGravity(Gravity.CENTER);
			produceUnitIn.setText(list.getString("produceUnit"));
			produceUnitIn.setGravity(Gravity.CENTER);
			speedIn.setText(list.getString("speed"));
			speedIn.setGravity(Gravity.CENTER);
			typeIn.setText(list.getString("type"));
			deviceNameIn.setText(list.getString("deviceName"));
			deviceNameIn.setGravity(Gravity.CENTER);
			checkNameIn.setText(list.getString("checkName"));
			checkNameIn.setGravity(Gravity.CENTER);
			riQiIn1.setText(list.getString("riQi1"));
			formatter = new SimpleDateFormat ("yyyy-MM-dd");       
			curDate = new Date(System.currentTimeMillis());//获取当前时间  
			riQiIn1.setGravity(Gravity.CENTER);
			riQiIn2.setText(formatter.format(curDate));
			riQiIn2.setGravity(Gravity.CENTER);
			jieGuo1.setText(list.getString("carMin")+"/"+list.getString("carMax"));
			jieGuo1.setGravity(Gravity.CENTER);
			jieLun1.setText(list.getString("isOK1"));
			jieLun1.setGravity(Gravity.CENTER);
			jieGuo2.setText(list.getString("counterMin")+"/"+list.getString("counterMax"));
			jieGuo2.setGravity(Gravity.CENTER);
			jieLun2.setText(list.getString("isOK2"));
			jieLun2.setGravity(Gravity.CENTER);
			jieGuo3.setText(list.getString("overMin")+"/"+list.getString("overMax"));
			jieGuo3.setGravity(Gravity.CENTER);
			jieLun3.setText(list.getString("isOK3"));
			jieLun3.setGravity(Gravity.CENTER);
			if(list.getString("isOK1").trim().equals("合格") && list.getString("isOK2").trim().equals("合格") && list.getString("isOK3").trim().equals("合格")){
						resultIn.setText("合格");
			}else
				resultIn.setText("不合格");

			jieLun3.setGravity(Gravity.CENTER);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
