package com.example.my_bus_info;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) public class Passenger_Search extends Activity {
    Spinner roott,stop,dir;
    Button srch;
    ListView lv;
    String a,b,c;
    
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passenger__search);
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

		stop=(Spinner)findViewById(R.id.spinner1);
		roott=(Spinner)findViewById(R.id.spinner2);
	    dir=(Spinner)findViewById(R.id.spinner3);
		srch=(Button)findViewById(R.id.button1);
		lv=(ListView)findViewById(R.id.listView1);
		
		
		
		SoapObject sobj=new SoapObject(soapclass.NAMESPACE,"slct_name");
		soapclass sc=new soapclass();
		String ou=sc.Callsoap(sobj, "http://tempuri.org/slct_name");
		String[] s1 = ou.split("@");
		
		ArrayAdapter<String> allist = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, s1);
		roott.setAdapter(allist);
		
		
		
		roott.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (!roott.getSelectedItem().toString().equals("select")) {
					String []k=roott.getSelectedItem().toString().split("-");
					a=k[0];
					listt();
					Query2();
				} else {

				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		
		stop.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (!stop.getSelectedItem().toString().equals("select")) {
				String []k = stop.getSelectedItem().toString().split("-");
				b=k[0];
				} else {

				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		srch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SoapObject sobj=new SoapObject(soapclass.NAMESPACE,"search");
				
				sobj.addProperty("id",stop.getSelectedItem().toString());
				sobj.addProperty("rid",a);
				sobj.addProperty("dir",dir.getSelectedItem().toString());
				soapclass sc=new soapclass();
				String ou=sc.Callsoap(sobj, "http://tempuri.org/search");

				ArrayList<HashMap<String,String>>allist=new ArrayList<HashMap<String, String>>();
				String []s1=ou.split("#");
				for(int i=0;i<s1.length;i++)
				{
				String []s2=s1[i].split("@");
				HashMap<String,String>hmap=new HashMap<String,String>();
				hmap.put("a", s2[0]);
				hmap.put("b", s2[1]);
				hmap.put("c", s2[2]);
				hmap.put("d", s2[3]);
				
				allist.add(hmap);
				
				}
				ListAdapter lis=new SimpleAdapter(getApplicationContext(), allist, R.layout.four, new String[] {"a","b","c","d"}, new int[] {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4});
				lv.setAdapter(lis);	
				
			}
		});
	}

	public void listt()
	{
		SoapObject sobj1=new SoapObject(soapclass.NAMESPACE,"slct_stop");
		sobj1.addProperty("ID",a);
		soapclass sc1=new soapclass();
		String ou1=sc1.Callsoap(sobj1, "http://tempuri.org/slct_stop");
		String[] s11 = ou1.split("@");
		ArrayAdapter<String> allistt = new ArrayAdapter<String>(Passenger_Search.this,
				android.R.layout.simple_spinner_item, s11);
		stop.setAdapter(allistt);
	}
	
	public void Query2()
	{
		SoapObject sobj2=new SoapObject(soapclass.NAMESPACE,"slct_dire");
		soapclass sc2=new soapclass();
		sobj2.addProperty("busno",a);
		
		String ou2=sc2.Callsoap(sobj2, "http://tempuri.org/slct_dire");
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
		getMenuInflater().inflate(R.menu.passenger__search, menu);
		return true;
	}

}
