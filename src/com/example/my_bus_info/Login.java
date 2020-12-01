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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Login extends Activity {
	EditText usrnm, psswd;
	Button log;
	TextView tv;
	public static String uid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		try {
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
		} catch (Exception e) {

		}

		usrnm = (EditText) findViewById(R.id.editText1);
		psswd = (EditText) findViewById(R.id.editText2);
		log = (Button) findViewById(R.id.button1);
		tv = (TextView) findViewById(R.id.textView3);
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Reg_Typ.class);
				startActivity(i);
			}
		});
		log.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SoapObject sobj = new SoapObject(soapclass.NAMESPACE, "login");
				sobj.addProperty("Username", usrnm.getText().toString());
				sobj.addProperty("Password", psswd.getText().toString());
				soapclass sc = new soapclass();
				String ou = sc.Callsoap(sobj, "http://tempuri.org/login");
				if (!ou.equals(""))
					if (!ou.equals("error")) {
						String[] k = ou.split("#");
						uid = k[0];
						String ty = k[1];
						if (ty.equals("passenger")) {
							Intent i = new Intent(getApplicationContext(),
									Passenger_Search.class);
							startActivity(i);
						} else if (ty.equals("owner")) {
							Toast.makeText(getApplicationContext(), "success",
									3).show();
							Intent i = new Intent(getApplicationContext(),
									Owner_Home.class);
							startActivity(i);
						}
					} else {
						Toast.makeText(getApplicationContext(), "Login Failed",
								5).show();
					}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
