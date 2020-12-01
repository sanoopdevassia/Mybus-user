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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) public class Change_Details extends Activity {
	Spinner busno;
	EditText chngbsnm;
	Button sav;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change__details);
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
		chngbsnm=(EditText)findViewById(R.id.editText1);
		//psswd=(EditText)findViewById(R.id.editText2);
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
				SoapObject sobj=new SoapObject(soapclass.NAMESPACE,"ChangeDetails");
				sobj.addProperty("busno",busno.getSelectedItem().toString());
				sobj.addProperty("chngbsnm",chngbsnm.getText().toString());
				//sobj.addProperty("psswd",psswd.getText().toString());
				soapclass sc=new soapclass();
				String ou=sc.Callsoap(sobj, "http://tempuri.org/ChangeDetails");
				
				if(!ou.equals("eroor"))
				{
					Toast.makeText(getApplicationContext(), "Success", 5).show();
				}
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change__details, menu);
		return true;
	}

}
