package com.example.my_bus_info;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Time extends Activity {
	Spinner busno,bustp,dir;
	EditText chntym;
	Button sv;
	String ss;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time);
		busno=(Spinner)findViewById(R.id.spinner1);
		dir=(Spinner)findViewById(R.id.spinner3);
		bustp=(Spinner)findViewById(R.id.spinner2);
		chntym=(EditText)findViewById(R.id.editText1);
		sv=(Button)findViewById(R.id.button1);
		
		SoapObject sobj=new SoapObject(soapclass.NAMESPACE,"slct_busno_OW");
		sobj.addProperty("owid", Login.uid);
		soapclass sc=new soapclass();
		String ou=sc.Callsoap(sobj, "http://tempuri.org/slct_busno_OW");
		String[] s1 = ou.split("@");
		ArrayAdapter<String> allist = new ArrayAdapter<String>(Time.this,
				android.R.layout.simple_spinner_item, s1);
		busno.setAdapter(allist);
		
		
		busno.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ss=busno.getSelectedItem().toString();
				Query1();
				Query2();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		sv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SoapObject sobj=new SoapObject(soapclass.NAMESPACE,"timesche");
				sobj.addProperty("busid",busno.getSelectedItem().toString());
				sobj.addProperty("sid",bustp.getSelectedItem().toString());
				sobj.addProperty("time",chntym.getText().toString());
				sobj.addProperty("dir",dir.getSelectedItem().toString());
				soapclass sc=new soapclass();
				String ou=sc.Callsoap(sobj, "http://tempuri.org/timesche");
				

				if(!ou.equals("eroor"))
				{
					Toast.makeText(getApplicationContext(), "Success", 5).show();
				}
				
				}
				

				
			
		});
		
	}

	public void Query1()
	{
		SoapObject sobj2=new SoapObject(soapclass.NAMESPACE,"slct_Stop");
		soapclass sc2=new soapclass();
		sobj2.addProperty("busno",ss);
		
		String ou2=sc2.Callsoap(sobj2, "http://tempuri.org/slct_Stop");
		String[] s12 = ou2.split("@");
		ArrayAdapter<String> alllist = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, s12);
		bustp.setAdapter(alllist);
	}
	
	public void Query2()
	{
		SoapObject sobj2=new SoapObject(soapclass.NAMESPACE,"slct_direction");
		soapclass sc2=new soapclass();
		sobj2.addProperty("busno",ss);
		
		String ou2=sc2.Callsoap(sobj2, "http://tempuri.org/slct_direction");
		String []s12=ou2.split("#");
		for(int i=0;i<s12.length;i++)
		{
		//String []s2=s12[i].split("@");
		//String[] s12 = ou2.split("#");
		ArrayAdapter<String> alllist = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, s12);
		
		dir.setAdapter(alllist);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time, menu);
		return true;
	}

}
