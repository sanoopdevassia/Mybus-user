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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Passenger_Registration extends Activity {
	EditText nam, phno, emal, pswd, cnfmpsd;
	Button sub;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passenger__registration);
		try {
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
		} catch (Exception e) {

		}

		nam = (EditText) findViewById(R.id.editText1);
		phno = (EditText) findViewById(R.id.editText2);
		emal = (EditText) findViewById(R.id.editText3);
		pswd = (EditText) findViewById(R.id.editText4);
		cnfmpsd = (EditText) findViewById(R.id.editText5);
		sub = (Button) findViewById(R.id.button1);
		sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String eml=emal.getText().toString();
				String emailPattern = "[a-zA-Z0-9._-]+@gmail+\\.+com+";
			    if(eml.matches(emailPattern))
				{
				
				if (nam.getText().toString().equals("")
						|| phno.getText().toString().equals("")
						|| emal.getText().toString().equals("")
						|| pswd.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "Fields are null",5).show();
				}

				else {
					if (pswd.getText().toString().equals(cnfmpsd.getText().toString())) {

						// string nam, string phno, string emal, string pswd
						SoapObject sobj = new SoapObject(soapclass.NAMESPACE,"passengerreg");
						sobj.addProperty("nam", nam.getText().toString());
						// Toast.makeText(getApplicationContext(),
						// nam.getText().toString(), 5).show();
						sobj.addProperty("phno", phno.getText().toString());
						sobj.addProperty("emal", emal.getText().toString());
						sobj.addProperty("pswd", pswd.getText().toString());
						soapclass sc = new soapclass();
						String ou = sc.Callsoap(sobj,"http://tempuri.org/passengerreg");
						Toast.makeText(getApplicationContext(),"Passenger Registered", 3).show();
					} 
					else {
						Toast.makeText(getApplicationContext(),"Incorrect password", 3).show();
					}
				}
			}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.passenger__registration, menu);
		return true;
	}

}
