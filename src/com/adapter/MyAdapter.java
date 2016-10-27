package com.adapter;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.elevator.R;
import com.view.CHScrollView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {  
    public static HashMap<Integer, Boolean> isSelected;  
    private LayoutInflater inflater = null;  
	private List<CHScrollView> mHScrollViews;
	private ListView mListView;
    private JSONArray list ;
    public MyAdapter( ListView mListView,List<CHScrollView> mHScrollViews,JSONArray list2, Activity activity) {  
        this.list = list2;
        this.mHScrollViews = mHScrollViews;
        inflater = LayoutInflater.from(activity);  
        isSelected = new HashMap<Integer, Boolean>();  
        // ��ʼ������  
        initDate();  
    }  
  
    // ��ʼ��isSelected������  
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
    	ViewHolder holder;
        if (view == null) {  
        	holder=new ViewHolder();
            view = inflater.inflate(R.layout.activity_mission_item, null);   
            //��������������Ϊһ�����廬�����������һ��һ���Ļ�
			addHViews((CHScrollView) view.findViewById(R.id.item_scroll));
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
        try {
			holder.addressItem.setText(list.getJSONObject(position).getString("address").trim());
	        holder.reportNoItem.setText(list.getJSONObject(position).getString("reportNo").trim());
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
	        return view; 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
  
        // ����isSelected������checkbox��ѡ��״��  
        holder.cb.setChecked(getIsSelected().get(position));   
        return view;
    }  
  
    public static HashMap<Integer,Boolean> getIsSelected() {  
        return isSelected;  
    }  
  
    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {  
        MyAdapter.isSelected = isSelected;  
    } 
    public void addHViews(final CHScrollView hScrollView) {
		if (!mHScrollViews.isEmpty()) {
			int size = mHScrollViews.size();
			CHScrollView scrollView = mHScrollViews.get(size - 1);
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
 