package com.example.tab;

public class PatientClass {
	private String FName;
	private String LName;
	private String PreferredName;
	
	public PatientClass(){
	}
	public String getFname()
	{
		return this.FName;
	}
	
	public String getLname(){
		return this.LName;
	}
	public String getPreferredName(){
		return this.PreferredName;
	}
	
	public void setName(String fname, String lname){
		this.FName=fname;
		this.LName=lname;
	}
	public void setEmail(String email){
		this.PreferredName=email;
	}
}
