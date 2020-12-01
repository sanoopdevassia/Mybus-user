package com.example.my_bus_info;

import org.ksoap2.serialization.SoapObject;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) public class AddBus extends Activity {
	EditText busnm,busno,src,dest;
	Spinner rt;
	Button sub;
	 
	String a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bus);
		try
		{
			if(android.os.Build.VERSION.SDK_INT>9)
			{
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
		}
		catch(Exception e)
		{
		
		}

		busnm=(EditText)findViewById(R.id.editText1);
		busno=(EditText)findViewById(R.id.editText2);
		src=(EditText)findViewById(R.id.editText3);
		dest=(EditText)findViewById(R.id.ed_dest);
		rt=(Spinner)findViewById(R.id.spinner1);
		sub=(Button)findViewById(R.id.button1);
		SoapObject sobj=new SoapObject(soapclass.NAMESPACE,"slct_name");
		soapclass sc=new soapclass();
		String ou=sc.Callsoap(sobj, "http://tempuri.org/slct_name");
		String[] s1 = ou.split("@");
		ArrayAdapter<String> allist = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, s1);
		rt.setAdapter(allist);
		
		
		rt.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (!rt.getSelectedItem().toString().equals("select")) {
					String []k=rt.getSelectedItem().toString().split("-");
					a=k[0];
					
				} else {

				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(busnm.getText().toString().equals("") || busno.getText().toString().equals("") || src.getText().toString().equals("")||
						dest.getText().toString().equals("")||rt.getSelectedItem().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(), "Fields are null", 5).show();
				}
				else
				{
				Toast.makeText(getApplicationContext(), Login.uid, 5).show();
				SoapObject sobj=new SoapObject(soapclass.NAMESPACE,"businfo");
				sobj.addProperty("Bus_Name",busnm.getText().toString());
				sobj.addProperty("Bus_Number",busno.getText().toString());
				sobj.addProperty("Source",src.getText().toString());
				sobj.addProperty("Dest",dest.getText().toString());
				sobj.addProperty("oid",Login.uid);
				sobj.addProperty("Route",a);
				soapclass sc=new soapclass();
				String ou=sc.Callsoap(sobj, "http://tempuri.org/businfo");
				if(!ou.equals("error"))
				{
					Toast.makeText(getApplicationContext(), "Success", 5).show();
				}
				else {
					Toast.makeText(getApplicationContext(), "Failed to add", 5).show();
				}
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_bus, menu);
		return true;
	}

}
