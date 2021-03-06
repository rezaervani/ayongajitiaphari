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

public class AdapterRiyadhusShalihin extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data; 
	private static LayoutInflater inflater=null;
	public ImageLoader imageLoader;
	
	public AdapterRiyadhusShalihin(Activity a, ArrayList<HashMap<String, String>> d) {
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
			vi = inflater.inflate(R.layout.barisriyadhusshalihin, null);
		
		ImageView tekshaditsrs = (ImageView)vi.findViewById(R.id.tekshadisrs);
		TextView terjemah = (TextView)vi.findViewById(R.id.terjemah);
		TextView derajat = (TextView)vi.findViewById(R.id.derajat);
		TextView pengesahan = (TextView)vi.findViewById(R.id.pengesahan);
		TextView syarah = (TextView)vi.findViewById(R.id.syarah);
		
		
		HashMap<String, String> ayat = new HashMap<String, String>();
		ayat = data.get(position);
		
		//Mengeset semuanya di listview
		imageLoader.DisplayImage(ayat.get(RiyadhusShalihin.KEY_THUMB_URL), tekshaditsrs);
		terjemah.setText(ayat.get(RiyadhusShalihin.TAG_TERJEMAH));
		derajat.setText(ayat.get(RiyadhusShalihin.TAG_DERAJAT));
		pengesahan.setText(ayat.get(RiyadhusShalihin.TAG_PENGESAHAN));
		syarah.setText(ayat.get(RiyadhusShalihin.TAG_SYARAH));
		
		
		return vi;
		
		
		
	}
	
	

}
