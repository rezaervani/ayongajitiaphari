package com.eclipseprogramming.ayongajitiaphari;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DaftarSurah extends ListActivity {
	
	private ProgressDialog infoNunggu;
	
	private static String url = "http://rumahilmu.net/eclipseprogramming/daftarsurah.php";
	
	private static final String TAG_SURAHQURAN 		= "surahquran";
	private static final String TAG_ID 				= "id";
	private static final String TAG_NAMASURAH 		= "namasurah";
	private static final String TAG_KETERANGANSURAH = "keterangansurah";
	
	// Menghubungi JSONArray
	JSONArray alquran = null;
		
	// Membuat HashMap untuk ListView
	ArrayList<HashMap<String, String>> surahAlQuran;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tafsirquran);
		
		surahAlQuran = new ArrayList<HashMap<String, String>>();
		
		ListView lv = getListView();
	        
	    
	}
	
	private class GetSurah extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			PanggilanHTTP panggil = new PanggilanHTTP();
			
			String strnyajson = panggil.makeServiceCall(url, PanggilanHTTP.GET);
			
			Log.d("Response : ", " >" + strnyajson);
			
			if (strnyajson != null) {
    			try {
    				JSONObject objeknyajson = new JSONObject(strnyajson);
    				
    				//Memperolah node JSON Array
    				alquran = objeknyajson.getJSONArray(TAG_SURAHQURAN);
    				
    				// Looping seluruh jadwal
    				for (int i = 0; i < alquran.length(); i++) {
    					JSONObject d 		= alquran.getJSONObject(i);
    					String id 			= d.getString(TAG_ID);
    					String surah 		= d.getString(TAG_NAMASURAH);
    					String keterangan 	= d.getString(TAG_KETERANGANSURAH);
    					
    					
    					HashMap<String, String> daftarquran = new HashMap<String,String>();
    					
    					daftarquran.put(TAG_ID, id);
    					daftarquran.put(TAG_NAMASURAH, surah);
    					daftarquran.put(TAG_KETERANGANSURAH, keterangan);
    					
    					surahAlQuran.add(daftarquran);
    					
    				}
    			} catch (JSONException ex) {
					ex.printStackTrace();
    			}
				} else {
					Log.e("PanggilanHTTP", "Tidak dapat data apapun dari URL tersebut");
	    		}
    	
			
			
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			infoNunggu = new ProgressDialog(DaftarSurah.this);
			infoNunggu.setMessage("Meloading Quran dari rumahilmu.net ... ");
			infoNunggu.setCancelable(false);
			infoNunggu.show();
			
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (infoNunggu.isShowing())
				infoNunggu.dismiss();
			
			ListAdapter adaptersurah = new SimpleAdapter(DaftarSurah.this, surahAlQuran, R.layout.tafsirquran, new String[]
					
					{TAG_NAMASURAH, TAG_KETERANGANSURAH },
					
					new int[] { R.id.namasurah, R.id.keterangan});
			
			setListAdapter(adaptersurah);
			
		}

		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}