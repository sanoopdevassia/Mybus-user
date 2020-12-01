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
import android.widget.RadioButton;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Owner_Registration extends Activity {
	EditText nam, phno, emil, psswd, cnfrmpsd;
	RadioButton m, f;
	Button reg;
	String gndr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owner__registration);
		try {
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
		} catch (Exception e) {

		}

		// ownrid=(EditText)findViewById(R.id.editText1);
		nam = (EditText) findViewById(R.id.editText2);
		phno = (EditText) findViewById(R.id.editText3);
		emil = (EditText) findViewById(R.id.editText4);
		psswd = (EditText) findViewById(R.id.editText5);
		cnfrmpsd = (EditText) findViewById(R.id.editText6);
		m = (RadioButton) findViewById(R.id.radio0);
		f = (RadioButton) findViewById(R.id.radio1);
		reg = (Button) findViewById(R.id.button1);
		reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (m.isChecked()) {
					gndr = m.getText().toString();
				} else {
					gndr = f.getText().toString();
				}
				String eml=emil.getText().toString();
				String emailPattern = "[a-zA-Z0-9._-]+@gmail+\\.+com+";
			    if(eml.matches(emailPattern))
				{
				


				if (nam.getText().toString().equals("")
						|| phno.getText().toString().equals("")
						|| emil.getText().toString().equals("")
						|| psswd.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "Fields are null",
							5).show();
				} else {
					if (psswd.getText().toString()
							.equals(cnfrmpsd.getText().toString())) {
						SoapObject sobj = new SoapObject(soapclass.NAMESPACE,
								"OwnerReg");
						// sobj.addProperty("oid",ownrid.getText().toString());
						sobj.addProperty("nam", nam.getText().toString());
						sobj.addProperty("phno", phno.getText().toString());
						sobj.addProperty("email", emil.getText().toString());
						sobj.addProperty("psswd", psswd.getText().toString());
						sobj.addProperty("gender", gndr);
						soapclass sc = new soapclass();
						String ou = sc.Callsoap(sobj,
								"http://tempuri.org/OwnerReg");
						if (!ou.equals("error")) {
							Toast.makeText(getApplicationContext(),
									"Registered", 3).show();
						} else {
							Toast.makeText(getApplicationContext(),
									"Not Registered", 3).show();
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"Incorrect password", 3).show();
					}
				}
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.owner__registration, menu);
		return true;
	}

}
