package com.example.tab;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditPatientProfile extends Activity{
	
	private SQLiteDatabase db;
	private ExternalDbOpenHelper dbHelper; 
	
	private EditText fname;
	private EditText lname;
	private EditText pname;
	private Spinner glassSpinner;
	private String glassChoice;
	private Spinner hearSpinner;
	private String hearChoice;
	
	private EditText reason;
	private EditText hospital;
	private EditText building;
	private EditText city;
	
	private EditText roomNum;
	private EditText floor;
	
	private Spinner liquidThicknessSpinner;
	private String liquidChoice;
	private Spinner dietConsistencySpinner;
	private String dietChoice;
	
	private boolean isUpdate=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_patient_profile);
		
		dbHelper = new ExternalDbOpenHelper(this, "patientInfo");
		db= dbHelper.openDataBase();
		Cursor c= db.rawQuery("SELECT * FROM "+ "user", null);
		
		fname=(EditText)findViewById(R.id.editFname);
		lname=(EditText)findViewById(R.id.editLname);
		pname=(EditText)findViewById(R.id.editPreferredName);
		
		//About me part
		glassSpinner = (Spinner)findViewById(R.id.wearGlassSpinner);		
		ArrayAdapter<CharSequence> glassAdapter = ArrayAdapter.createFromResource(this,
		        R.array.glass_array, android.R.layout.simple_spinner_item);
		glassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		glassSpinner.setAdapter(glassAdapter);
		glassSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		            int position, long id) {
		        glassChoice = parent.getItemAtPosition(position).toString();
		        //Toast.makeText(EditPatientProfile.this, glassChoice, 1000).show();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // TODO Auto-generated method stub
		    }
		});
		
		hearSpinner = (Spinner)findViewById(R.id.useHearingAideSpinner);
		ArrayAdapter<CharSequence> hearAdapter = ArrayAdapter.createFromResource(this,
		        R.array.hearaide_array, android.R.layout.simple_spinner_item);
		hearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		hearSpinner.setAdapter(hearAdapter);
		hearSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		            int position, long id) {
		        hearChoice = parent.getItemAtPosition(position).toString();
		        //Toast.makeText(EditPatientProfile.this, hearChoice, 1000).show();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // TODO Auto-generated method stub
		    }
		});
		
		//Where I Am part
		reason = (EditText)findViewById(R.id.editReason);
		hospital = (EditText)findViewById(R.id.editHospitalName);
		building = (EditText)findViewById(R.id.editBuildingName);
		city = (EditText)findViewById(R.id.editCityName);
		
		//In Dodd Hall part
		roomNum = (EditText)findViewById(R.id.editRoomNumber);
		floor = (EditText)findViewById(R.id.editRoomFloor);
		
		//My Diet part
		liquidThicknessSpinner = (Spinner)findViewById(R.id.liquidThicknessSpinner);
		ArrayAdapter<CharSequence> liquidAdapter = ArrayAdapter.createFromResource(this,
		        R.array.liquidThickness_array, android.R.layout.simple_spinner_item);
		liquidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		liquidThicknessSpinner.setAdapter(liquidAdapter);
		liquidThicknessSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		            int position, long id) {
		        liquidChoice = parent.getItemAtPosition(position).toString();
		        //Toast.makeText(EditPatientProfile.this, liquidChoice, 1000).show();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // TODO Auto-generated method stub
		    }
		});
		
		dietConsistencySpinner = (Spinner)findViewById(R.id.dietConsistencySpinner);
		ArrayAdapter<CharSequence> dietAdapter = ArrayAdapter.createFromResource(this,
		        R.array.dietConsistency_array, android.R.layout.simple_spinner_item);
		dietAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dietConsistencySpinner.setAdapter(dietAdapter);
		dietConsistencySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		            int position, long id) {
		        dietChoice = parent.getItemAtPosition(position).toString();
		        //Toast.makeText(EditPatientProfile.this, dietChoice, 1000).show();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // TODO Auto-generated method stub
		    }
		});
		
		int count= c.getCount();
		if(count!=0){
			c.moveToFirst();
			fname.setText(c.getString(c.getColumnIndexOrThrow("Fname")));
			lname.setText(c.getString(c.getColumnIndexOrThrow("Lname")));
			pname.setText(c.getString(c.getColumnIndexOrThrow("PreferredName")));
			glassChoice = c.getString(c.getColumnIndexOrThrow("WearGlass"));
			hearChoice = c.getString(c.getColumnIndexOrThrow("UseHearAid"));
			
			//Where I Am part
			reason.setText(c.getString(c.getColumnIndexOrThrow("ReasonInHospital")));
			hospital.setText(c.getString(c.getColumnIndexOrThrow("NameHospital")));
			building.setText(c.getString(c.getColumnIndexOrThrow("NameBuilding")));;
			city.setText(c.getString(c.getColumnIndexOrThrow("City")));
			
			//Dodd 
			roomNum.setText(c.getString(c.getColumnIndexOrThrow("RoomNo")));
			floor.setText(c.getString(c.getColumnIndexOrThrow("Floor")));
			
			//Diet
			liquidChoice = c.getString(c.getColumnIndexOrThrow("LiquidThickness"));
			dietChoice = c.getString(c.getColumnIndexOrThrow("Diet"));
			
			isUpdate=true;
		}
		else{
			isUpdate=false;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_patient_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void BackToMainScreen(View view){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void SavePatientInfo(View view){
		
		
		//Cursor c= db.rawQuery("SELECT * FROM "+ "user", null);
		
		//int count= c.getCount();
		
		ContentValues values = new ContentValues();
		values.put("Fname", fname.getText().toString());
		values.put("Lname", lname.getText().toString());
		values.put("PreferredName", pname.getText().toString());
		values.put("WearGlass", glassChoice);
		values.put("UseHearAid", hearChoice);
		
		values.put("ReasonInHospital", reason.getText().toString());
		values.put("NameHospital", hospital.getText().toString());
		values.put("NameBuilding", building.getText().toString());
		values.put("City", city.getText().toString());
		
		values.put("RoomNo", roomNum.getText().toString());
		values.put("Floor", floor.getText().toString());
		
		values.put("LiquidThickness", liquidChoice);
		values.put("Diet", dietChoice);
		if(isUpdate==false){
			db.insert("user", null, values);
		}
		else{
			String filter="id=1";
			db.update("user", values, filter, null);
		}
		db.close();
		dbHelper.close();
		BackToMainScreen(view);
		
	}

}
