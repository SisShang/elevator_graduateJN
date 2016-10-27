package com.adapter;

import android.widget.Button;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.audit.AuditActivity;
import com.audit.reportSee;
import com.example.elevator.R;
import com.view.auditCHS;

public class auditAdapter extends BaseAdapter {  
    public static HashMap<Integer, Boolean> isSelected;  
    private LayoutInflater inflater = null;  
	private List<auditCHS> mHScrollViews;
	private ListView mListView;
    private JSONArray list ;
    AuditActivity activity;
    public auditAdapter( ListView mListView,List<auditCHS> mHScrollViews,JSONArray list2, Activity activity) {  
        System.out.println("do");
    	this.list = list2;
        this.mHScrollViews = mHScrollViews;
        this.activity=(AuditActivity) activity;
        inflater = LayoutInflater.from(activity);  
        isSelected = new HashMap<Integer, Boolean>();  
        // 初始化数据  
        initDate();  
    }  
  
    // 初始化isSelected的数据  
    private void initDate(){  
        for(int i=0; i<list.length();i++) {  
            getIsSelected().put(i,false);  
        }  
    } 
    @Override  
    public int getCount() {  
        return list.length();  
    }  
  
    @Override  
    public Object getItem(int position) { 
			return list.optJSONObject(position);  
    }  
  
    @Override  
    public long getItemId(int position) {  
    	 try{  
             return ((JSONObject)getItem(position)).getInt("reportNo");  
         }catch(JSONException e){  
             e.printStackTrace();  
         }  
         return 0;   
    }  
  
    @Override  
    public View getView(final int position, View view, ViewGroup parent) { 
    	final ViewHolder holder;
        if (view == null) {  
        	holder=new ViewHolder();
            view = inflater.inflate(R.layout.activity_audit_item, null);             
            //作用是所有数据为一个整体滑动，否则就是一条一条的滑
			addHViews((auditCHS) view.findViewById(R.id.item_scroll));
            holder.addressItem = (TextView) view.findViewById(R.id.addressItem); 
            holder.reportNoItem1 = (Button) view.findViewById(R.id.reportNoItem);
            holder.contactItem = (TextView) view.findViewById(R.id.contactItem); 
            holder.phoneNoItem = (TextView) view.findViewById(R.id.phoneNoItem);
            holder.unitItem = (TextView) view.findViewById(R.id.unitItem);
            holder.deviceNoItem=(TextView) view.findViewById(R.id.deviceNoItem);
            holder.governorDiameterItem=(TextView) view.findViewById(R.id.governorDiameterItem);
            holder.governorNoItem=(TextView) view.findViewById(R.id.governorNoItem);
            holder.governorPerimeterItem=(TextView) view.findViewById(R.id.governorPerimeterItem);
            holder.governorSpecItem=(TextView) view.findViewById(R.id.governorSpecItem);
            holder.produceTimeItem=(TextView) view.findViewById(R.id.produceTimeItem);
            holder.produceUnitItem=(TextView) view.findViewById(R.id.produceUnitItem);
            holder.speedItem=(TextView) view.findViewById(R.id.speedItem);
            holder.carSpeed1Item=(TextView) view.findViewById(R.id.carSpeed1Item);
            holder.carSpeed2Item=(TextView) view.findViewById(R.id.carSpeed2Item);
            holder.carSpeed3Item=(TextView) view.findViewById(R.id.carSpeed3Item);
            holder.carSpeedAveItem=(TextView) view.findViewById(R.id.carSpeedAveItem);
            holder.carSpeedTimeItem=(TextView) view.findViewById(R.id.carSpeedTimeItem);
            holder.counterSpeed1Item=(TextView) view.findViewById(R.id.counterSpeed1Item);
            holder.counterSpeed2Item=(TextView) view.findViewById(R.id.counterSpeed2Item);
            holder.counterSpeed3Item=(TextView) view.findViewById(R.id.counterSpeed3Item);
            holder.counterSpeedAveItem=(TextView) view.findViewById(R.id.counterSpeedAveItem);
            holder.counterSpeedTimeItem=(TextView) view.findViewById(R.id.counterSpeedTimeItem);
            holder.overSpeed1Item=(TextView) view.findViewById(R.id.overSpeed1Item);
            holder.overSpeed2Item=(TextView) view.findViewById(R.id.overSpeed2Item);
            holder.overSpeed3Item=(TextView) view.findViewById(R.id.overSpeed3Item);
            holder.overSpeedAveItem=(TextView) view.findViewById(R.id.overSpeedAveItem);
            holder.overSpeedTimeItem=(TextView) view.findViewById(R.id.overSpeedTimeItem);
            holder.cb = (CheckBox) view.findViewById(R.id.item_cb);
            // 为view设置标签  
            view.setTag(holder);  
        } else {  
            // 取出holder  
            holder = (ViewHolder) view.getTag();  
            }  
  
  
        // 设置list中TextView的显示  
        try {
			holder.addressItem.setText(list.getJSONObject(position).getString("address").trim());
	        holder.reportNoItem1.setText(list.getJSONObject(position).getString("reportNo").trim());
	        holder.contactItem.setText(list.getJSONObject(position).getString("contact").trim());
	        holder.phoneNoItem.setText(list.getJSONObject(position).getString("phoneNo").trim()); 
	        holder.unitItem.setText(list.getJSONObject(position).getString("unit").trim()); 
	        holder.deviceNoItem.setText(list.getJSONObject(position).getString("deviceNo").trim());
	        holder.governorDiameterItem.setText(list.getJSONObject(position).getString("diameter").trim());
	        holder.governorNoItem.setText(list.getJSONObject(position).getString("governorNo").trim());
	        holder.governorPerimeterItem.setText(list.getJSONObject(position).getString("perimeter").trim());
	        holder.governorSpecItem.setText(list.getJSONObject(position).getString("governorSpec").trim());
	        holder.produceTimeItem.setText(list.getJSONObject(position).getString("produceTime").trim());
	        holder.produceUnitItem.setText(list.getJSONObject(position).getString("produceUnit").trim());
	        holder.speedItem.setText(list.getJSONObject(position).getString("speed").trim());
	        holder.carSpeed1Item.setText(list.getJSONObject(position).getString("carSpeed1").trim());
	        holder.carSpeed2Item.setText(list.getJSONObject(position).getString("carSpeed2").trim());
	        holder.carSpeed3Item.setText(list.getJSONObject(position).getString("carSpeed3").trim());
	        holder.carSpeedAveItem.setText(list.getJSONObject(position).getString("carSpeedAve").trim());
	        holder.carSpeedTimeItem.setText(list.getJSONObject(position).getString("carSpeedTime").trim());
	        holder.counterSpeed1Item.setText(list.getJSONObject(position).getString("counterSpeed1").trim());
	        holder.counterSpeed2Item.setText(list.getJSONObject(position).getString("counterSpeed2").trim());
	        holder.counterSpeed3Item.setText(list.getJSONObject(position).getString("counterSpeed3").trim());
	        holder.counterSpeedAveItem.setText(list.getJSONObject(position).getString("counterSpeedAve").trim());
	        holder.counterSpeedTimeItem.setText(list.getJSONObject(position).getString("counterSpeedTime").trim());
	        holder.overSpeed1Item.setText(list.getJSONObject(position).getString("overSpeed1").trim());
	        holder.overSpeed2Item.setText(list.getJSONObject(position).getString("overSpeed2").trim());
	        holder.overSpeed3Item.setText(list.getJSONObject(position).getString("overSpeed3").trim());
	        holder.overSpeedAveItem.setText(list.getJSONObject(position).getString("overSpeedAve").trim());
	        holder.overSpeedTimeItem.setText(list.getJSONObject(position).getString("overSpeedTime").trim());
	        // 根据isSelected来设置checkbox的选中状况  
	        holder.cb.setChecked(getIsSelected().get(position)); 
	        // 监听checkBox并根据原来的状态来设置新的状态  
	        holder.cb.setOnClickListener(new View.OnClickListener() {  
	  
	            public void onClick(View v) {  
	  
	                if (isSelected.get(position)) {  
	                    isSelected.put(position, false);  
	                    setIsSelected(isSelected);  
	                } else {  
	                    isSelected.put(position, true);  
	                    setIsSelected(isSelected);  
	                }  
	                notifyDataSetChanged();
	  
	            }  
	        });  
	        holder.reportNoItem1.setOnClickListener(new View.OnClickListener() {  
	  
	            public void onClick(View v) {  
	            	int k=0;

            		try {
	            	for(int i=0;i<list.length();i++)
	            	{
							if(list.getJSONObject(i).getString("reportNo").trim().equals(holder.reportNoItem1.getText()))
							{
								k=i;break;
							}
						}
	            	} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	Intent intent=new Intent();
	            	try {
						intent.putExtra("list",list.getJSONObject(k).toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			intent.setClass(activity, reportSee.class);
	    			activity.startActivity(intent);
	            }  
	        });  
	        return view; 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
  
        // 根据isSelected来设置checkbox的选中状况  
        holder.cb.setChecked(getIsSelected().get(position));   
        return view;
    }  
  
    public static HashMap<Integer,Boolean> getIsSelected() {  
        return isSelected;  
    }  
  
    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {  
        MyAdapter.isSelected = isSelected;  
    } 
    public void addHViews(final auditCHS hScrollView) {
		if (!mHScrollViews.isEmpty()) {
			int size = mHScrollViews.size();
			auditCHS scrollView = mHScrollViews.get(size - 1);
			final int scrollX = scrollView.getScrollX();
			// 第一次满屏后，向下滑动，有一条数据在开始时未加入
			if (scrollX != 0) {
				mListView.post(new Runnable() {
					@Override
					public void run() {
						// 当listView刷新完成之后，把该条移动到最终位置
						hScrollView.scrollTo(scrollX, 0);
					}
				});
			}
		}
		mHScrollViews.add(hScrollView);
	}
  
}  
 