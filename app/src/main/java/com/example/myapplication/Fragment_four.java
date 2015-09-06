package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_four extends Fragment {

	private int mDownStatus;
	String m;
	String path  = "/mnt/sdcard/";
	String url;
	DownUtil downUtil;
	ListView list;
	ProgressBar bar;
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
		bar = (ProgressBar) view.findViewById(R.id.bar);
		list = (ListView)view.findViewById(R.id.listView);

		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if (msg.what == 0x123)
				{
					bar.setProgress(mDownStatus);
					if(mDownStatus == 100)
						Toast.makeText(getActivity(), "下载完成！", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(getActivity(), "文件已下载到/mnt/sdcard/", Toast.LENGTH_LONG).show();
			}
		};
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
				item.put("time",files.get(a).getTime().substring(0, 11));
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
					try{
						File f = new File(n);
						if(!f.exists())
						{
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

									final Timer timer = new Timer();
									timer.schedule(new TimerTask()
									{
										@Override
										public void run()
										{
											// 获取下载任务的完成比率
											double completeRate = downUtil.getCompleteRate();
											mDownStatus = (int) (completeRate * 100);
											// 发送消息通知界面更新进度条
											handler.sendEmptyMessage(0x123);
											// 下载完全后取消任务调度
											if (mDownStatus >= 100)
											{
												timer.cancel();
											}
										}
									}, 0, 100);
								}
							}.start();
						}
						else
						{
							Intent i = openFile(n);
							startActivity(i);
						}
					}catch (Exception e) {

					}
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

	public static Intent openFile(String filePath){

		File file = new File(filePath);
		if(!file.exists()) return null;
        /* 取得扩展名 */
		String end=file.getName().substring(file.getName().lastIndexOf(".") + 1,file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
		if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")||
				end.equals("xmf")||end.equals("ogg")||end.equals("wav")){
			return getAudioFileIntent(filePath);
		}else if(end.equals("3gp")||end.equals("mp4")){
			return getAudioFileIntent(filePath);
		}else if(end.equals("jpg")||end.equals("gif")||end.equals("png")||
				end.equals("jpeg")||end.equals("bmp")){
			return getImageFileIntent(filePath);
		}else if(end.equals("apk")){
			return getApkFileIntent(filePath);
		}else if(end.equals("ppt")){
			return getPptFileIntent(filePath);
		}else if(end.equals("xls")){
			return getExcelFileIntent(filePath);
		}else if(end.equals("doc")){
			return getWordFileIntent(filePath);
		}else if(end.equals("pdf")){
			return getPdfFileIntent(filePath);
		}else if(end.equals("chm")){
			return getChmFileIntent(filePath);
		}else if(end.equals("txt")){
			return getTextFileIntent(filePath,false);
		}else{
			return getAllIntent(filePath);
		}
	}

	//Android获取一个用于打开APK文件的intent
	public static Intent getAllIntent( String param ) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri,"*/*");
		return intent;
	}
	//Android获取一个用于打开APK文件的intent
	public static Intent getApkFileIntent( String param ) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri,"application/vnd.android.package-archive");
		return intent;
	}

	//Android获取一个用于打开VIDEO文件的intent
	public static Intent getVideoFileIntent( String param ) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri, "video/*");
		return intent;
	}

	//Android获取一个用于打开AUDIO文件的intent
	public static Intent getAudioFileIntent( String param ){

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri, "audio/*");
		return intent;
	}

	//Android获取一个用于打开Html文件的intent
	public static Intent getHtmlFileIntent( String param ){

		Uri uri = Uri.parse(param ).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param ).build();
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setDataAndType(uri, "text/html");
		return intent;
	}

	//Android获取一个用于打开图片文件的intent
	public static Intent getImageFileIntent( String param ) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri, "image/*");
		return intent;
	}

	//Android获取一个用于打开PPT文件的intent
	public static Intent getPptFileIntent( String param ){

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
		return intent;
	}

	//Android获取一个用于打开Excel文件的intent
	public static Intent getExcelFileIntent( String param ){

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri, "application/vnd.ms-excel");
		return intent;
	}

	//Android获取一个用于打开Word文件的intent
	public static Intent getWordFileIntent( String param ){

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri, "application/msword");
		return intent;
	}

	//Android获取一个用于打开CHM文件的intent
	public static Intent getChmFileIntent( String param ){

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri, "application/x-chm");
		return intent;
	}

	//Android获取一个用于打开文本文件的intent
	public static Intent getTextFileIntent( String param, boolean paramBoolean){

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (paramBoolean){
			Uri uri1 = Uri.parse(param );
			intent.setDataAndType(uri1, "text/plain");
		}else{
			Uri uri2 = Uri.fromFile(new File(param ));
			intent.setDataAndType(uri2, "text/plain");
		}
		return intent;
	}
	//Android获取一个用于打开PDF文件的intent
	public static Intent getPdfFileIntent( String param ){

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param ));
		intent.setDataAndType(uri, "application/pdf");
		return intent;
	}

}