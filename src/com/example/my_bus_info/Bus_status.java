package com.example.my_bus_info;

import org.ksoap2.serialization.SoapObject;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) public class Bus_status extends Activity {
	Spinner busno;
	EditText statusbx;
	Button sav;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_status);
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

		busno=(Spinner)findViewById(R.id.spinner1);
		statusbx=(EditText)findViewById(R.id.editText1);
		sav=(Button)findViewById(R.id.button1);
		
		SoapObject sobj=new SoapObject(soapclass.NAMESPACE,"slct_busno_OW");
		sobj.addProperty("owid", Login.uid);
		soapclass sc=new soapclass();
		String ou=sc.Callsoap(sobj, "http://tempuri.org/slct_busno_OW");
		String[] s1 = ou.split("@");
		ArrayAdapter<String> allist = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, s1);
		busno.setAdapter(allist);
		
		sav.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SoapObject sob=new SoapObject(soapclass.NAMESPACE,"Busstatus");
				sob.addProperty("busno",busno.getSelectedItem().toString());
				sob.addProperty("statusbx",statusbx.getText().toString());
				soapclass s=new soapclass();
				String out=s.Callsoap(sob, "http://tempuri.org/Busstatus");
				

				if(!out.equals("eroor"))
				{
					Toast.makeText(getApplicationContext(), "Success", 5).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bus_status, menu);
		return true;
	}

}
