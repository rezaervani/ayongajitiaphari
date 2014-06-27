package com.eclipseprogramming.ayongajitiaphari;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterAyat extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<HashMap<String, String>> data; 
	private static LayoutInflater inflater=null;
	public ImageLoader imageLoader;
	
	public AdapterAyat(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		
		if(convertView==null)
			vi = inflater.inflate(R.layout.barislist, null);
		
		TextView terjemah = (TextView)vi.findViewById(R.id.terjemah);
		ImageView gambarayat = (ImageView)vi.findViewById(R.id.teksayat);
		
		HashMap<String, String> ayat = new HashMap<String, String>();
		ayat = data.get(position);
		
		//Mengeset semuanya di listview
		terjemah.setText(ayat.get(ListViewAyat.KEY_TERJEMAH));
		imageLoader.DisplayImage(ayat.get(ListViewAyat.KEY_THUMB_URL), gambarayat);
		
		return vi;
		
		
		
	}
	
	

}
