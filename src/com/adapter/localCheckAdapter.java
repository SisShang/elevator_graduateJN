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
	        // ��ʼ������  
	        initDate();  
	    }  
	  
	    // ��ʼ��isSelected������  
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
	            //��������������Ϊһ�����廬�����������һ��һ���Ļ�
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
	            // Ϊview���ñ�ǩ  
	            view.setTag(holder);  
	        } else {  
	            // ȡ��holder  
	            holder = (ViewHolder) view.getTag();  
	            }  
	  
	  
	        // ����list��TextView����ʾ  
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
		        // ����isSelected������checkbox��ѡ��״��  
		        holder.cb.setChecked(getIsSelected().get(position)); 
		        // ����checkBox������ԭ����״̬�������µ�״̬  
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
	       
	  
	        // ����isSelected������checkbox��ѡ��״��  
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
				// ��һ�����������»�������һ�������ڿ�ʼʱδ����
				if (scrollX != 0) {
					mListView.post(new Runnable() {
						@Override
						public void run() {
							// ��listViewˢ�����֮�󣬰Ѹ����ƶ�������λ��
							hScrollView.scrollTo(scrollX, 0);
						}
					});
				}
			}
			mHScrollViews.add(hScrollView);
		}
	  
	}  
	 
