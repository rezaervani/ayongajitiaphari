package com.eclipseprogramming.ayongajitiaphari;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DaftarSurah extends ListActivity {
	
	private ProgressDialog pDialog;
	
	// URL untuk mendapatkan JSON
	private static String url = "http://rumahilmu.net/eclipseprogramming/ayatquran.php";
	
	// JSON Node
	private static final String TAG_SURAHQURAN = "surahquran";
	private static final String TAG_ID = "id";
	private static final String TAG_NAMASURAH = "namasurah";
	private static final String TAG_KETERANGAN = "keterangan";
	
	
	
	// Menghubungi JSONArray
	JSONArray alquran = null;
	
	// Membuat HashMap untuk ListView
	ArrayList<HashMap<String, String>> daftarSurah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tafsirquran);
        
        daftarSurah = new ArrayList<HashMap<String, String>>();
        
        ListView lv = getListView();
        
        new GetJadwal().execute();
        
        
    }
    
    private class GetJadwal extends AsyncTask<Void, Void, Void> {
    	
    	
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		
    		//menampilkan progress dialog
    		pDialog = new ProgressDialog(DaftarSurah.this);
    		pDialog.setMessage("Sedang Memuat dari rumahilmu.net");
    		pDialog.setCancelable(false);
    		pDialog.show();
    		
    	}

    	@Override
    	protected Void doInBackground(Void... params) {
    		
    		// Membuat Class Instance Service Handler
    		PanggilanHTTP ph = new PanggilanHTTP();
    		
    		String jsonStr = ph.makeServiceCall(url, PanggilanHTTP.GET);
    		
    		Log.d("Response: ", "> " + jsonStr);
    		
    		if (jsonStr != null) {
    			try {
    				JSONObject jsonObj = new JSONObject(jsonStr);
    				
    				//Memperolah node JSON Array
    				alquran = jsonObj.getJSONArray(TAG_SURAHQURAN);
    				
    				// Looping seluruh jadwal
    				for (int i = 0; i < alquran.length(); i++) {
    					JSONObject c 	= alquran.getJSONObject(i);
    					String id 		= c.getString(TAG_ID);
    					String namasurah 	= c.getString(TAG_NAMASURAH);
    					String keterangan 	= c.getString(TAG_KETERANGAN);
    					
    					
    					
    					HashMap<String, String> jadwal = new HashMap<String,String>();
    					
    					jadwal.put(TAG_ID, id);
    					jadwal.put(TAG_NAMASURAH, namasurah);
    					jadwal.put(TAG_KETERANGAN, keterangan);
    					
    					
    					
    					daftarSurah.add(jadwal);
    					
    					//menambahkan jadwal ke jadwal list
    				}
    				} catch (JSONException e) {
    					e.printStackTrace();
    					
    				}
    			
    			} else {
    				Log.e("PanggilanHTTP", "Tidak dapat data apapun dari URL tersebut");
    			}
    			
    			return null;
    				
    				}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			ListAdapter adapter = new SimpleAdapter(DaftarSurah.this, daftarSurah, R.layout.ayatquran, new String[]
					
					{TAG_NAMASURAH, TAG_KETERANGAN  },
					
					new int[] { R.id.namasurah, R.id.keterangan});
			
			setListAdapter(adapter);
			
		}
    	
	}
    		
}






