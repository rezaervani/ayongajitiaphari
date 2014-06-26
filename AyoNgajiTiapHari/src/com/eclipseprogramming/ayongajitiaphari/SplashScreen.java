package com.eclipseprogramming.ayongajitiaphari;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {
	
	private static int waktukemunculan = 10000;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layarsplash);
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(SplashScreen.this, TombolMasuk.class);
				startActivity(i);
				
				finish();
			}
			
		
			
		}, waktukemunculan);
		
	}
	
	

}

