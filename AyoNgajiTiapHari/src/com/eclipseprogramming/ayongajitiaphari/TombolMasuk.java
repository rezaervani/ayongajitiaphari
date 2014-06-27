package com.eclipseprogramming.ayongajitiaphari;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class TombolMasuk extends Activity {
	Button tombolquran;
	Button tombolsunnah;
	Button tombolmasjid;
	Button tomboltanyajawab;
	Button tombolinfo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tombolmasuk);
		
		tombolquran 		= (Button) findViewById(R.id.button1);
		tombolsunnah 		= (Button) findViewById(R.id.button2);
		tombolmasjid		= (Button) findViewById(R.id.button3);
		tomboltanyajawab	= (Button) findViewById(R.id.button4);
		tombolinfo			= (Button) findViewById(R.id.button5);
		
		tombolquran.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent bukatafsir = new Intent(TombolMasuk.this, DaftarSurah.class);
				startActivity(bukatafsir);
				
			}
			
			
		});
		
		tombolmasjid.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent bukajadwal = new Intent(TombolMasuk.this, MainActivity.class);
				startActivity(bukajadwal);
				
			}
			
		}
		
			);		
				
		tombolsunnah.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent bukasunnah = new Intent(TombolMasuk.this, KitabHadits.class);
				startActivity(bukasunnah);
				
			}
			
		}
		
			);		
		
		tombolinfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent bukaayat = new Intent(TombolMasuk.this, ListViewAyat.class);
				startActivity(bukaayat);
				
			}
			
		}
		
			);		
			
		
	}
	

}
