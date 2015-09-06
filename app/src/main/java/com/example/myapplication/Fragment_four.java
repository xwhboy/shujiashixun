package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_four extends Fragment {

	String m;
	String path  = "/mnt/sdcard/";
	String url;
	DownUtil downUtil;
	ListView list;
	SimpleAdapter adapter = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.resource, container, false);

		list = (ListView)view.findViewById(R.id.listView);
		try {
			Gson gson = new Gson();
			String json = Data.checkUpdate(Person._ID);
			List<Shared_File> files = gson.fromJson(json, new TypeToken<List<Shared_File>>(){}.getType());
			List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
			for (int a = 0; a < files.size() ; a++)
			{
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("id", files.get(a).getId());
				item.put("title", files.get(a).getTitle());
				item.put("time",files.get(a).getTime().substring(0,11));
				item.put("teacher", files.get(a).getTeacher());
				item.put("url", files.get(a).getUrl());
				item.put("fileName", files.get(a).getFilename());
				data.add(item);
			}

			adapter = new SimpleAdapter(getActivity(),data,
					R.layout.hk_item, new String[] { "title", "time" ,"teacher","fileName"},
					new int[] { R.id.hk_title, R.id.hk_data, R.id.hk_teacher,R.id.hk_content });
			list.setAdapter(adapter);
			list.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView Adapter, View view, int position,long id)
				{
					HashMap<String, Object> item = new HashMap<String, Object>();
					item = (HashMap<String, Object>) adapter.getItem(position);
					url =  (String) item.get("url");
					try {
						m = URLEncoder.encode((String) item.get("fileName"),"UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					final String u = url + m;
					final String n = path + (String) item.get("fileName");

					new Thread()
					{
						@Override
						public void run()
						{
							try
							{
								downUtil = new DownUtil(u,
										n, 1 );
								downUtil.download();
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					}.start();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}


		return view;

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}