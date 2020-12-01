package com.example.my_bus_info;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Passenger_homepage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passenger_homepage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.passenger_homepage, menu);
		return true;
	}

}
