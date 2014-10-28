package com.example.tab.fragment;

import com.example.tab.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GoHomeFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		View rootView =inflater.inflate(R.layout.gohome, container,false);
		return rootView;
	}
}
