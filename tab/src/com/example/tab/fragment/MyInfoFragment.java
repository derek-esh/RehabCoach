package com.example.tab.fragment;

import java.io.File;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tab.ExternalDbOpenHelper;
import com.example.tab.R;

public class MyInfoFragment extends Fragment implements OnClickListener{
	
	private Button b1;
	private Button b2;
	private ImageView img;
	
	private TextView Fname;
	private TextView Lname;
	private TextView Preferredname;
	private TextView Wearglasss;
	private TextView Hearaide;
	private TextView Reason;
	private TextView Hospitalname;
	private TextView Buildingname;
	private TextView Cityname;
	private TextView Roomnumber;
	private TextView Floor;
	private TextView Liquidthickness;
	private TextView Dietconsistency;
	
	private TextView temp;
	
	private int SCALE = 3;
	private Uri photoUri;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.myinfo, container, false);
        /*set image */
		b1 = (Button)rootView.findViewById(R.id.button1);
		b1.setOnClickListener(this);
		b2 = (Button)rootView.findViewById(R.id.button2);
		b2.setOnClickListener(this);
		img = (ImageView)rootView.findViewById(R.id.imageView1);
		img.setImageResource(R.drawable.default_photo);
		
		/*set info*/
		Fname=(TextView)rootView.findViewById(R.id.showFname);
		Lname=(TextView)rootView.findViewById(R.id.showLname);
		Preferredname=(TextView)rootView.findViewById(R.id.showPreferredName);
		Wearglasss=(TextView)rootView.findViewById(R.id.showGlassChoice);
		Hearaide=(TextView)rootView.findViewById(R.id.showHearChoice);
		Reason=(TextView)rootView.findViewById(R.id.showReason);
		Hospitalname=(TextView)rootView.findViewById(R.id.showHospitalName);
		Buildingname=(TextView)rootView.findViewById(R.id.showBuildingName);
		Cityname=(TextView)rootView.findViewById(R.id.showCityName);
		Roomnumber=(TextView)rootView.findViewById(R.id.showRoomNumber);
		Floor=(TextView)rootView.findViewById(R.id.showFloor);
		Liquidthickness=(TextView)rootView.findViewById(R.id.showLiquidThickness);
		Dietconsistency=(TextView)rootView.findViewById(R.id.showDietConsistency);
		
		
		ExternalDbOpenHelper mDbHelper = new ExternalDbOpenHelper(getActivity(),"patientInfo");
		mDbHelper.createDataBase();
		SQLiteDatabase db= mDbHelper.openDataBase();
		Cursor c= db.rawQuery("SELECT * FROM "+ "user", null);
		
		int count= c.getCount();
		Log.d("count",Integer.toString(count));
		int isVisible=0;
		if(count==0){
			isVisible = View.GONE;
			temp=(TextView)rootView.findViewById(R.id.textView1);
			temp.setText("You have not created any patient profile yet. Click on the menu to create one for them!");
			temp.setTextSize(40);
		}
		
		else{
			temp=(TextView)rootView.findViewById(R.id.textView1);
			temp.setVisibility(View.GONE);
			isVisible = View.VISIBLE;
			c.moveToFirst();
			
			Fname.setText(c.getString(c.getColumnIndexOrThrow("Fname")));
			Lname.setText(c.getString(c.getColumnIndexOrThrow("Lname")));
			Preferredname.setText(c.getString(c.getColumnIndexOrThrow("PreferredName")));
			Wearglasss.setText(c.getString(c.getColumnIndexOrThrow("WearGlass")));
			Hearaide.setText(c.getString(c.getColumnIndexOrThrow("UseHearAid")));
			
			Reason.setText(c.getString(c.getColumnIndexOrThrow("ReasonInHospital")));
			Hospitalname.setText(c.getString(c.getColumnIndexOrThrow("NameHospital")));
			Buildingname.setText(c.getString(c.getColumnIndexOrThrow("NameBuilding")));
			Cityname.setText(c.getString(c.getColumnIndexOrThrow("City")));
			
			Roomnumber.setText(c.getString(c.getColumnIndexOrThrow("RoomNo")));
			Floor.setText(c.getString(c.getColumnIndexOrThrow("Floor")));
			
			Liquidthickness.setText(c.getString(c.getColumnIndexOrThrow("LiquidThickness")));
			Dietconsistency.setText(c.getString(c.getColumnIndexOrThrow("Diet")));
		}
		
		b1.setVisibility(isVisible);
		b2.setVisibility(isVisible);
		img.setVisibility(isVisible);
		Fname.setVisibility(isVisible);
		Lname.setVisibility(isVisible);
		Preferredname.setVisibility(isVisible);
		Wearglasss.setVisibility(isVisible);
		Hearaide.setVisibility(isVisible);
		Reason.setVisibility(isVisible);
		Hospitalname.setVisibility(isVisible);
		Buildingname.setVisibility(isVisible);
		Cityname.setVisibility(isVisible);
		Roomnumber.setVisibility(isVisible);
		Floor.setVisibility(isVisible);
		Liquidthickness.setVisibility(isVisible);
		Dietconsistency.setVisibility(isVisible);
		
		
		temp=(TextView)rootView.findViewById(R.id.Fnametitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Lnametitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Preferrednametitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Wearglasstitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Hearaidetitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Reasontitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Hospitalnametitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Buildingnametitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Citynametitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Roomnumbertitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Floortitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Liquidthicknesstitle);
		temp.setVisibility(isVisible);
		
		temp=(TextView)rootView.findViewById(R.id.Dietconsistencytitle);
		temp.setVisibility(isVisible);
		c.close();
		db.close();
		mDbHelper.close();
        
        return rootView;
    }
	

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.button1:
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			startActivityForResult(intent, 1);
			break;
		case R.id.button2:
			File file1 = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
		    Bitmap photo = BitmapFactory.decodeFile(file1.getAbsolutePath());
		    Bitmap smallBitmap = zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
		    img.setImageBitmap(smallBitmap);
		}		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{  
	    //Check that request code matches ours:
	    if (requestCode == 1) 
	    {
	        //Get our saved file into a bitmap object:
	       File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
	       Bitmap photo = BitmapFactory.decodeFile(file.getAbsolutePath());
	       Bitmap smallBitmap = zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
	       img.setImageBitmap(smallBitmap);
	    }
	}
	
	public Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }
}
