package com.eclipseprogramming.ayongajitiaphari;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.eclipseprogramming.ayongajitiaphari.R;

public class LemparSementara extends Activity {
	
	private static final String TAG_KODE = "kode";
	private static final String TAG_KETERANGAN = "keterangan";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sementara);
		
		Intent bukamushaf = getIntent();
		
		String keterangan = bukamushaf.getStringExtra(TAG_KETERANGAN);
		
		
		TextView lblText = (TextView) findViewById(R.id.teksdariintentsebelumnya);
		
		lblText.setText(keterangan);
		
	}
	
	

}
