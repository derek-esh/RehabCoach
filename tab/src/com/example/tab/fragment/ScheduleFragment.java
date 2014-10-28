package com.example.tab.fragment;

import com.example.tab.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;

public class ScheduleFragment extends Fragment {
	CalendarView  calendar;
	View rootView;
	String record="aa";
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.schedule, container, false);
        calendar = (CalendarView)rootView.findViewById(R.id.calender);
        
       /* calendar.setOnDateChangeListener(new OnDateChangeListener(){
    	@Override
    	public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth ){
    		record=dayOfMonth+"/"+month+"/"+year;
    	}
        });
        Log.d("aaa",record);
        TextView temp= (TextView)rootView.findViewById(R.id.showCalendar);
        temp.setText(record);*/
        return rootView;
    }
	
}
