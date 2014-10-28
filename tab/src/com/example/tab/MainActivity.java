package com.example.tab;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tab.fragment.GoHomeFragment;
import com.example.tab.fragment.MyInfoFragment;
import com.example.tab.fragment.MyTeamFragment;
import com.example.tab.fragment.ScheduleFragment;

public class MainActivity extends FragmentActivity implements TabListener {

	private ViewPager mViewPager;
	public static final int MAX_TAB_SIZE = 4;
	public static final String ARGUMENTS_NAME = "args";
	private TabFragmentPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById();
		initView();
	}

	private void findViewById() {
		mViewPager = (ViewPager) this.findViewById(R.id.pager);
	}

	private void initView() {
		
		final ActionBar mActionBar = getActionBar();
		
		mActionBar.setDisplayHomeAsUpEnabled(false);
		
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				
				mActionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		//Initialize ActionBar
		for(int i=0;i<MAX_TAB_SIZE;i++){
			Tab tab = mActionBar.newTab();
			tab.setText(mAdapter.getPageTitle(i)).setTabListener(this);
			setTabIcon(tab, i);
			mActionBar.addTab(tab);			
		}
		
		
	}
	
	public void setTabIcon(Tab t, int i){
		switch(i){
		case 0:
			t.setIcon(R.drawable.ic_action_person);
			break;
		case 1:
			t.setIcon(R.drawable.ic_action_group);
			break;
		case 2:
			t.setIcon(R.drawable.ic_action_event);
			break;
		case 3:
			t.setIcon(R.drawable.ic_action_place);
			break;
		}
	}

	public static class TabFragmentPagerAdapter extends FragmentPagerAdapter{

		public TabFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment ft = null;
			switch (arg0) {
			case 0:
				ft = new MyInfoFragment();
				break;
			case 1:
				ft = new MyTeamFragment();
				break;
			case 2:
				ft = new ScheduleFragment();
				break;
			case 3:
				ft = new GoHomeFragment();
				break;
			}
			return ft;
		}

		@Override
		public int getCount() {			
			return MAX_TAB_SIZE;
		}
		
		@Override
        public CharSequence getPageTitle(int position) {
			CharSequence r = "";
            switch (position){
            case 0:
            	r = "My Info";
            	break;
            case 1:
            	r = "My Team";
            	break;
            case 2:
            	r = "My Schedule";
            	break;
            case 3:
            	r = "Go Home";
            	break;
            }
            return r;
        }
		 
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        //return true;
    	MenuInflater inflater= getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
    	return super.onCreateOptionsMenu(menu);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
        	case R.id.editProfile:
        		openEditProfile();
        		return true;
        	default:
        		return super.onOptionsItemSelected(item); 
        }
    }
	
	public void openEditProfile(){
		Intent intent= new Intent(this,EditPatientProfile.class);
		startActivity(intent);
		//finish();
	}

}

