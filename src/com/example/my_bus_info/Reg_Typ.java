package com.example.my_bus_info;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class Reg_Typ extends Activity {
	RadioButton b,p;
	Button nxt;
	String typ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg__typ);
		
		b=(RadioButton)findViewById(R.id.radio0);
		p=(RadioButton)findViewById(R.id.radio1);
		nxt=(Button)findViewById(R.id.button1);
		nxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(b.isChecked()==true)
				{
					Intent i=new Intent(getApplicationContext(), Owner_Registration.class);
					startActivity(i);
				}
				else
				{
					Intent i=new Intent(getApplicationContext(), Passenger_Registration.class);
					startActivity(i);	
				}
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reg__typ, menu);
		return true;
	}

}
