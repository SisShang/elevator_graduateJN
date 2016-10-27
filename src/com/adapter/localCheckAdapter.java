package com.adapter;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.database.User;
import com.example.elevator.R;
import com.view.checkGoCHS;

public class localCheckAdapter extends BaseAdapter{
	public static HashMap<Integer, Boolean> isSelected; 
	    private LayoutInflater inflater = null;  
		private List<checkGoCHS> mHScrollViews;
		private ListView mListView;
	    private List<User> list ;
	    public localCheckAdapter( ListView mListView,List<checkGoCHS> mHScrollViews,List<User> list2, Activity activity) {  
	        this.list = list2;
	        this.mHScrollViews = mHScrollViews;
	        inflater = LayoutInflater.from(activity);  
	        isSelected = new HashMap<Integer, Boolean>(); 
	        // 初始化数据  
	        initDate();  
	    }  
	  
	    // 初始化isSelected的数据  
	    private void initDate(){  
	        for(int i=0; i<list.size();i++) {  
	            getIsSelected().put(i,false);  
	        }  
	    } 
	    
	    @Override  
	    public int getCount() {  
	        return list.size();  
	    }  
	  
	    @Override  
	    public Object getItem(int position) { 
				return list.get(position);  
	    }  
	  
	    @Override  
	    public long getItemId(int position) { 
	    	return Long.parseLong(list.get(position).getReportNo().trim());
	    }  
	  
	    @Override  
	    public View getView(final int position, View view, ViewGroup parent) { 
	    	ViewHolder holder;
	        if (view == null) {  
	        	holder=new ViewHolder();
	            view = inflater.inflate(R.layout.activity_mission_local, null); 
	            //作用是所有数据为一个整体滑动，否则就是一条一条的滑
				addHViews((checkGoCHS) view.findViewById(R.id.item_scroll));
	            holder.addressItem = (TextView) view.findViewById(R.id.addressItem); 
	            holder.reportNoItem = (TextView) view.findViewById(R.id.reportNoItem);
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
	            holder.cb = (CheckBox) view.findViewById(R.id.item_cb);
	            // 为view设置标签  
	            view.setTag(holder);  
	        } else {  
	            // 取出holder  
	            holder = (ViewHolder) view.getTag();  
	            }  
	  
	  
	        // 设置list中TextView的显示  
				holder.addressItem.setText(list.get(position).getAddress().trim());
		        holder.reportNoItem.setText(list.get(position).getReportNo().trim());
		        holder.contactItem.setText(list.get(position).getContact().trim());
		        holder.phoneNoItem.setText(list.get(position).getPhoneNo().trim()); 
		        holder.unitItem.setText(list.get(position).getUnit().trim()); 
		        holder.deviceNoItem.setText(list.get(position).getDeviceNo().trim());
		        holder.governorDiameterItem.setText(list.get(position).getGovernorDiameter().trim());
		        holder.governorNoItem.setText(list.get(position).getGovernorNo().trim());
		        holder.governorPerimeterItem.setText(list.get(position).getGovernorPerimeter().trim());
		        holder.governorSpecItem.setText(list.get(position).getGovernorSpec().trim());
		        holder.produceTimeItem.setText(list.get(position).getProduceTime().trim());
		        holder.produceUnitItem.setText(list.get(position).getProduceUnit().trim());
		        holder.speedItem.setText(list.get(position).getSpeed().trim()); 
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
	       
	  
	        // 根据isSelected来设置checkbox的选中状况  
	        holder.cb.setChecked(getIsSelected().get(position));    
	        return view;
	    }  
	  
	    public static HashMap<Integer,Boolean> getIsSelected() {  
	        return isSelected;  
	    }    
	  
	    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {  
	        localCheckAdapter.isSelected = isSelected;  
	    } 
	    public void addHViews(final checkGoCHS hScrollView) {
			if (!mHScrollViews.isEmpty()) {
				int size = mHScrollViews.size();
				checkGoCHS scrollView = mHScrollViews.get(size - 1);
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
	 
