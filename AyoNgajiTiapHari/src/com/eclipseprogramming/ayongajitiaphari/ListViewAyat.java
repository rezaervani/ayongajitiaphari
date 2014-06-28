package com.eclipseprogramming.ayongajitiaphari;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListViewAyat extends Activity {
	
	private ProgressDialog dialogp;
	
	private static final String TAG_KODE = "kode";
	
	// XML node keys
	static final String KEY_SONG = "ayatquran"; // parent node
	static final String KEY_ID = "Id";
	static final String KEY_TERJEMAH = "terjemah";
	static final String KEY_THUMB_URL = "urlgambar";
	
	String url = null;
	
	ListView list;
    AdapterAyat adapter;
    TextView labeltersembunyi;
    
   ArrayList<HashMap<String, String>> ayatayatQuran = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.penampilayat);
		
		//memperoleh data intent
		Intent bukamushaf = getIntent();
				
		// mendapatkan nilai JSON dari intent sebelumnya
		String kode = bukamushaf.getStringExtra(TAG_KODE);
				
		String urlnyanih = "http://rumahilmu.net/eclipseprogramming/" + kode + ".xml";
		
		TextView labeltersembunyi = (TextView) findViewById(R.id.sembunyilagi);
		
		
		labeltersembunyi.setText(urlnyanih);
		
		
		new GetAyat().execute();
		
	}
	
	
	
	private class GetAyat extends AsyncTask<Void, Void, Void> {
		
	
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			dialogp = new ProgressDialog(ListViewAyat.this);
			dialogp.setMessage("Memuat Data dari rumahilmu.net");
			dialogp.setCancelable(false);
			dialogp.show();
		}


		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = ((TextView) findViewById(R.id.sembunyilagi)).getText().toString();
			
			//Memanggil XMLParser (Panggilan HTTP)
			XMLParser parser = new XMLParser();
			
			String xml = parser.getXmlFromUrl(url, XMLParser.GET); 
			
			Log.d("Response: ", "> " + xml);
			
		if(xml != null) {
			try {
			
			Document doc = parser.getDomElement(xml);
			
			NodeList nl = doc.getElementsByTagName(KEY_SONG);
			
			//looping
			for (int i = 0; i < nl.getLength(); i++) {
				
				//Membuat HashMap 
				HashMap<String, String> map = new HashMap<String, String>();
				Element ayatnya = (Element) nl.item(i);
				
				//Memasukkan node childe ke HashMap => value
				map.put(KEY_ID, parser.getValue(ayatnya, KEY_ID));
				map.put(KEY_TERJEMAH, parser.getValue(ayatnya, KEY_TERJEMAH));
				map.put(KEY_THUMB_URL, parser.getValue(ayatnya, KEY_THUMB_URL));
				
				//menambahkan HashList ke ArrayList
				ayatayatQuran.add(map);
			}
			
		} catch (DOMException e) {
			e.printStackTrace();
		}
		} else {
			Log.d("XMLParser", "Tidak Mendapatkan Data");
		}
			
			return null;
		}
	
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (dialogp.isShowing())
				dialogp.dismiss();
			
			list = (ListView) findViewById(R.id.listayat);
		
			adapter = new AdapterAyat(ListViewAyat.this, ayatayatQuran);
			
			list.setAdapter(adapter);
			
			
		}
	
	}		
        
}
			

