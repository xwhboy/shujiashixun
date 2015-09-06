package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment_two extends Fragment {
	DBAdapter db;
	TextView b11, b22, b23, b24, b25, b26 ,b32, b33, b34, b35, b36, b42, b43, b44,
			b45, b46, b52, b53, b54, b55, b56, b62, b63, b64, b65, b66 ,b72,
			b73, b74, b75, b76,  b82, b83, b84, b85, b86 ,b92, b93,b94,b95,b96;


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return super.onContextItemSelected(item);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.course_fragment, container, false);
		init(view);
		return view;
	}

	public void init(View view) {
		b11 = (TextView) view.findViewById(R.id.a11);

		b22 = (TextView) view.findViewById(R.id.a22);
		b23 = (TextView) view.findViewById(R.id.a23);
		b24 = (TextView) view.findViewById(R.id.a24);
		b25 = (TextView) view.findViewById(R.id.a25);
		b26 = (TextView) view.findViewById(R.id.a26);


		b32 = (TextView) view.findViewById(R.id.a32);
		b33 = (TextView) view.findViewById(R.id.a33);
		b34 = (TextView) view.findViewById(R.id.a34);
		b35 = (TextView) view.findViewById(R.id.a35);
		b36 = (TextView) view.findViewById(R.id.a36);


		b42 = (TextView) view.findViewById(R.id.a42);
		b43 = (TextView) view.findViewById(R.id.a43);
		b44 = (TextView) view.findViewById(R.id.a44);
		b45 = (TextView) view.findViewById(R.id.a45);
		b46 = (TextView) view.findViewById(R.id.a46);


		b52 = (TextView) view.findViewById(R.id.a52);
		b53 = (TextView) view.findViewById(R.id.a53);
		b54 = (TextView) view.findViewById(R.id.a54);
		b55 = (TextView) view.findViewById(R.id.a55);
		b56 = (TextView) view.findViewById(R.id.a56);


		b62 = (TextView) view.findViewById(R.id.a62);
		b63 = (TextView) view.findViewById(R.id.a63);
		b64 = (TextView) view.findViewById(R.id.a64);
		b65 = (TextView) view.findViewById(R.id.a65);
		b66 = (TextView) view.findViewById(R.id.a66);

		b72 = (TextView) view.findViewById(R.id.a72);
		b73 = (TextView) view.findViewById(R.id.a73);
		b74 = (TextView) view.findViewById(R.id.a74);
		b75 = (TextView) view.findViewById(R.id.a75);
		b76 = (TextView) view.findViewById(R.id.a76);

		b82 = (TextView) view.findViewById(R.id.a82);
		b83 = (TextView) view.findViewById(R.id.a83);
		b84 = (TextView) view.findViewById(R.id.a84);
		b85 = (TextView) view.findViewById(R.id.a85);
		b86 = (TextView) view.findViewById(R.id.a86);

		b92 = (TextView) view.findViewById(R.id.a92);
		b93 = (TextView) view.findViewById(R.id.a93);
		b94 = (TextView) view.findViewById(R.id.a94);
		b95 = (TextView) view.findViewById(R.id.a95);
		b96 = (TextView) view.findViewById(R.id.a96);


	}

	@Override
	public void onResume() {
		db = new DBAdapter(getActivity());
		db.open();
		Cursor cursor = null;
		cursor = db.getAllTitles();
		if (cursor.getCount() < 2) {
			for (int i = 1; i <= 45; i++) {
				db.inserttable(i, " ", " ", " ", " ", " ", " ", " ");

			}
		}
		ReadDB.reading(getActivity(), b22, 2);
		ReadDB.reading(getActivity(), b23, 3);
		ReadDB.reading(getActivity(), b24, 4);
		ReadDB.reading(getActivity(), b25, 5);
		ReadDB.reading(getActivity(), b26, 6);

		ReadDB.reading(getActivity(), b32, 7);
		ReadDB.reading(getActivity(), b33, 8);
		ReadDB.reading(getActivity(), b34, 9);
		ReadDB.reading(getActivity(), b35, 10);
		ReadDB.reading(getActivity(), b36, 11);

		ReadDB.reading(getActivity(), b42, 12);
		ReadDB.reading(getActivity(), b43, 13);
		ReadDB.reading(getActivity(), b44, 14);
		ReadDB.reading(getActivity(), b45, 15);
		ReadDB.reading(getActivity(), b46, 16);

		ReadDB.reading(getActivity(), b52, 17);
		ReadDB.reading(getActivity(), b53, 18);
		ReadDB.reading(getActivity(), b54, 19);
		ReadDB.reading(getActivity(), b55, 20);
		ReadDB.reading(getActivity(), b56, 21);

		ReadDB.reading(getActivity(), b62, 22);
		ReadDB.reading(getActivity(), b63, 23);
		ReadDB.reading(getActivity(), b64, 24);
		ReadDB.reading(getActivity(), b65, 25);
		ReadDB.reading(getActivity(), b66, 26);

		ReadDB.reading(getActivity(), b72, 27);
		ReadDB.reading(getActivity(), b73, 28);
		ReadDB.reading(getActivity(), b74, 29);
		ReadDB.reading(getActivity(), b75, 30);
		ReadDB.reading(getActivity(), b76, 31);

		ReadDB.reading(getActivity(), b82, 32);
		ReadDB.reading(getActivity(), b83, 33);
		ReadDB.reading(getActivity(), b84, 34);
		ReadDB.reading(getActivity(), b85, 35);
		ReadDB.reading(getActivity(), b86, 36);

		ReadDB.reading(getActivity(), b92, 37);
		ReadDB.reading(getActivity(), b93, 38);
		ReadDB.reading(getActivity(), b94, 39);
		ReadDB.reading(getActivity(), b95, 40);
		ReadDB.reading(getActivity(), b96, 41);

		b11.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				update();
			}

		});
		b22.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Intent intent22 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 2);
				intent22.putExtras(bundle);
				startActivityForResult(intent22, 2);
				return true;
			}
		});
		b23.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent23 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 3);
				intent23.putExtras(bundle);
				startActivityForResult(intent23, 3);
				return true;
			}
		});
		b24.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent24 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 4);
				intent24.putExtras(bundle);
				startActivityForResult(intent24, 4);
				return true;

			}
		});
		b25.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent25 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 5);
				intent25.putExtras(bundle);
				startActivityForResult(intent25, 5);
				return true;

			}
		});
		b26.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent26 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 6);
				intent26.putExtras(bundle);
				startActivityForResult(intent26, 6);
				return true;

			}
		});



		b32.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent32 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 7);
				intent32.putExtras(bundle);
				startActivityForResult(intent32, 7);
				return true;

			}
		});
		b33.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent33 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 8);
				intent33.putExtras(bundle);
				startActivityForResult(intent33, 8);
				return true;

			}
		});
		b34.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent34 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 9);
				intent34.putExtras(bundle);
				startActivityForResult(intent34, 9);
				return true;

			}
		});
		b35.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent35 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 10);
				intent35.putExtras(bundle);
				startActivityForResult(intent35, 10);
				return true;

			}
		});
		b36.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent36 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 11);
				intent36.putExtras(bundle);
				startActivityForResult(intent36, 11);
				return true;

			}
		});


		b42.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent42 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 12);
				intent42.putExtras(bundle);
				startActivityForResult(intent42, 12);
				return true;

			}
		});
		b43.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent43 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 13);
				intent43.putExtras(bundle);
				startActivityForResult(intent43, 13);
				return true;
			}
		});
		b44.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent44 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 14);
				intent44.putExtras(bundle);
				startActivityForResult(intent44, 14);
				return true;

			}
		});
		b45.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent45 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 15);
				intent45.putExtras(bundle);
				startActivityForResult(intent45, 15);
				return true;

			}
		});
		b46.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent46 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 16);
				intent46.putExtras(bundle);
				startActivityForResult(intent46, 16);
				return true;

			}
		});


		b52.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent52 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 17);
				intent52.putExtras(bundle);
				startActivityForResult(intent52, 17);
				return true;

			}
		});
		b53.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent53 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 18);
				intent53.putExtras(bundle);
				startActivityForResult(intent53, 18);
				return true;

			}
		});
		b54.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent54 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 19);
				intent54.putExtras(bundle);
				startActivityForResult(intent54, 19);
				return true;

			}
		});
		b55.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent55 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 20);
				intent55.putExtras(bundle);
				startActivityForResult(intent55, 20);
				return true;

			}
		});
		b56.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent56 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 21);
				intent56.putExtras(bundle);
				startActivityForResult(intent56, 21);
				return true;

			}
		});


		b62.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent62 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 22);
				intent62.putExtras(bundle);
				startActivityForResult(intent62, 22);
				return true;

			}
		});
		b63.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent63 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 23);
				intent63.putExtras(bundle);
				startActivityForResult(intent63, 23);
				return true;

			}
		});
		b64.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent64 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 24);
				intent64.putExtras(bundle);
				startActivityForResult(intent64, 24);
				return true;

			}
		});
		b65.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent65 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 25);
				intent65.putExtras(bundle);
				startActivityForResult(intent65, 25);
				return true;

			}
		});

		b66.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent66 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 26);
				intent66.putExtras(bundle);
				startActivityForResult(intent66, 26);
				return true;

			}
		});



		b72.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent72 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 27);
				intent72.putExtras(bundle);
				startActivityForResult(intent72, 27);
				return true;

			}
		});
		b73.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent73 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 28);
				intent73.putExtras(bundle);
				startActivityForResult(intent73, 28);
				return true;

			}
		});
		b74.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent74 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 29);
				intent74.putExtras(bundle);
				startActivityForResult(intent74, 29);
				return true;

			}
		});
		b75.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent75 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 30);
				intent75.putExtras(bundle);
				startActivityForResult(intent75, 30);
				return true;

			}
		});
		b76.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent76 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 31);
				intent76.putExtras(bundle);
				startActivityForResult(intent76, 31);
				return true;

			}
		});


		b82.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent82 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 32);
				intent82.putExtras(bundle);
				startActivityForResult(intent82, 32);
				return true;

			}
		});
		b83.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent83 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 33);
				intent83.putExtras(bundle);
				startActivityForResult(intent83, 33);
				return true;

			}
		});
		b84.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent84 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 34);
				intent84.putExtras(bundle);
				startActivityForResult(intent84, 34);
				return true;

			}
		});
		b85.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent85 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 35);
				intent85.putExtras(bundle);
				startActivityForResult(intent85, 35);
				return true;

			}
		});
		b86.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent86 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 36);
				intent86.putExtras(bundle);
				startActivityForResult(intent86, 36);
				return true;

			}
		});

		b92.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent92 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 37);
				intent92.putExtras(bundle);
				startActivityForResult(intent92, 37);
				return true;

			}
		});
		b93.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent93 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 38);
				intent93.putExtras(bundle);
				startActivityForResult(intent93, 38);
				return true;

			}
		});
		b94.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent94 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 39);
				intent94.putExtras(bundle);
				startActivityForResult(intent94, 39);
				return true;

			}
		});
		b95.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent95 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 40);
				intent95.putExtras(bundle);
				startActivityForResult(intent95, 40);
				return true;

			}
		});
		b96.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stu
				Intent intent96 = new Intent(getActivity(), Detail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("key", 41);
				intent96.putExtras(bundle);
				startActivityForResult(intent96, 41);
				return true;

			}
		});


		super.onResume();
	}








	public void update()
	{
		Gson gson = new Gson();
		Week week = new Week();
		String json = Data.course(Person._ID,Person._grade,Person._class);
		week = gson.fromJson(json, Week.class);
		db.updateTitle(2, 2, week.Mon.first.getName(),
				week.Mon.first.getClassroom(), "######",
				week.Mon.first.getTeacher(), week.Mon.first.getWeekday(),week.Mon.first.getBeginweek(), week.Mon.first.getEndweek());
		ReadDB.reading(getActivity(), b22,2);
		db.updateTitle(7, 7, week.Mon.second.getName(),
				week.Mon.second.getClassroom(), "######",
				week.Mon.second.getTeacher(), week.Mon.second.getWeekday(),week.Mon.second.getBeginweek(), week.Mon.second.getEndweek());
		ReadDB.reading(getActivity(), b32,7);
		db.updateTitle(12, 12, week.Mon.third.getName(),
				week.Mon.third.getClassroom(), "######",
				week.Mon.third.getTeacher(), week.Mon.third.getWeekday(),week.Mon.third.getBeginweek(), week.Mon.third.getEndweek());
		ReadDB.reading(getActivity(), b42,12);
		db.updateTitle(17, 17, week.Mon.forth.getName(),
				week.Mon.forth.getClassroom(), "######",
				week.Mon.forth.getTeacher(), week.Mon.forth.getWeekday(),week.Mon.forth.getBeginweek(), week.Mon.forth.getEndweek());
		ReadDB.reading(getActivity(), b52,17);
		db.updateTitle(22, 22, week.Mon.fifth.getName(),
				week.Mon.fifth.getClassroom(), "######",
				week.Mon.fifth.getTeacher(), week.Mon.fifth.getWeekday(),week.Mon.fifth.getBeginweek(), week.Mon.fifth.getEndweek());
		ReadDB.reading(getActivity(), b62,2);
		db.updateTitle(27, 27, week.Mon.sixth.getName(),
				week.Mon.sixth.getClassroom(), "######",
				week.Mon.sixth.getTeacher(), week.Mon.sixth.getWeekday(),week.Mon.sixth.getBeginweek(), week.Mon.sixth.getEndweek());
		ReadDB.reading(getActivity(), b72,27);
		db.updateTitle(32, 32, week.Mon.senventh.getName(),
				week.Mon.senventh.getClassroom(), "######",
				week.Mon.senventh.getTeacher(), week.Mon.senventh.getWeekday(),week.Mon.senventh.getBeginweek(), week.Mon.senventh.getEndweek());
		ReadDB.reading(getActivity(), b82,32);
		db.updateTitle(37, 37, week.Mon.eighth.getName(),
				week.Mon.eighth.getClassroom(), "######",
				week.Mon.eighth.getTeacher(), week.Mon.eighth.getWeekday(),week.Mon.eighth.getBeginweek(), week.Mon.eighth.getEndweek());
		ReadDB.reading(getActivity(), b92,37);

		db.updateTitle(3, 3, week.Tues.first.getName(),
				week.Tues.first.getClassroom(), "######",
				week.Tues.first.getTeacher(), week.Tues.first.getWeekday(),week.Tues.first.getBeginweek(), week.Tues.first.getEndweek());
		ReadDB.reading(getActivity(), b23,3);
		db.updateTitle(8, 8, week.Tues.second.getName(),
				week.Tues.second.getClassroom(), "######",
				week.Tues.second.getTeacher(), week.Tues.second.getWeekday(),week.Tues.second.getBeginweek(), week.Tues.second.getEndweek());
		ReadDB.reading(getActivity(), b33,8);
		db.updateTitle(13, 13, week.Tues.third.getName(),
				week.Tues.third.getClassroom(), "######",
				week.Tues.third.getTeacher(), week.Tues.third.getWeekday(),week.Tues.third.getBeginweek(), week.Tues.third.getEndweek());
		ReadDB.reading(getActivity(), b43,13);
		db.updateTitle(18, 18, week.Tues.forth.getName(),
				week.Tues.forth.getClassroom(), "######",
				week.Tues.forth.getTeacher(), week.Tues.forth.getWeekday(),week.Tues.forth.getBeginweek(), week.Tues.forth.getEndweek());
		ReadDB.reading(getActivity(), b53,18);
		db.updateTitle(23, 23, week.Tues.fifth.getName(),
				week.Tues.fifth.getClassroom(), "######",
				week.Tues.fifth.getTeacher(), week.Tues.fifth.getWeekday(),week.Tues.fifth.getBeginweek(), week.Tues.fifth.getEndweek());
		ReadDB.reading(getActivity(), b63,23);
		db.updateTitle(28, 28, week.Tues.sixth.getName(),
				week.Tues.sixth.getClassroom(), "######",
				week.Tues.sixth.getTeacher(), week.Tues.sixth.getWeekday(),week.Tues.sixth.getBeginweek(), week.Tues.sixth.getEndweek());
		ReadDB.reading(getActivity(), b73,28);
		db.updateTitle(33, 33, week.Tues.senventh.getName(),
				week.Tues.senventh.getClassroom(), "######",
				week.Tues.senventh.getTeacher(), week.Tues.senventh.getWeekday(),week.Tues.senventh.getBeginweek(), week.Tues.senventh.getEndweek());
		ReadDB.reading(getActivity(), b83,33);
		db.updateTitle(38, 38, week.Tues.eighth.getName(),
				week.Tues.eighth.getClassroom(), "######",
				week.Tues.eighth.getTeacher(), week.Tues.eighth.getWeekday(),week.Tues.eighth.getBeginweek(), week.Tues.eighth.getEndweek());
		ReadDB.reading(getActivity(), b93,38);

		db.updateTitle(4, 4, week.Wed.first.getName(),
				week.Wed.first.getClassroom(), "######",
				week.Wed.first.getTeacher(), week.Wed.first.getWeekday(),week.Wed.first.getBeginweek(), week.Wed.first.getEndweek());
		ReadDB.reading(getActivity(), b24,4);
		db.updateTitle(9, 9, week.Wed.second.getName(),
				week.Wed.second.getClassroom(), "######",
				week.Wed.second.getTeacher(), week.Wed.second.getWeekday(),week.Wed.second.getBeginweek(), week.Wed.second.getEndweek());
		ReadDB.reading(getActivity(), b34,9);
		db.updateTitle(14, 14, week.Wed.third.getName(),
				week.Wed.third.getClassroom(), "######",
				week.Wed.third.getTeacher(), week.Wed.third.getWeekday(),week.Wed.third.getBeginweek(), week.Wed.third.getEndweek());
		ReadDB.reading(getActivity(), b44,14);
		db.updateTitle(19, 19, week.Wed.forth.getName(),
				week.Wed.forth.getClassroom(), "######",
				week.Wed.forth.getTeacher(), week.Wed.forth.getWeekday(),week.Wed.forth.getBeginweek(), week.Wed.forth.getEndweek());
		ReadDB.reading(getActivity(), b54,19);
		db.updateTitle(24, 24, week.Wed.fifth.getName(),
				week.Wed.fifth.getClassroom(), "######",
				week.Wed.fifth.getTeacher(), week.Wed.fifth.getWeekday(),week.Wed.fifth.getBeginweek(), week.Wed.fifth.getEndweek());
		ReadDB.reading(getActivity(), b64,24);
		db.updateTitle(29, 29, week.Wed.sixth.getName(),
				week.Wed.sixth.getClassroom(), "######",
				week.Wed.sixth.getTeacher(), week.Wed.sixth.getWeekday(),week.Wed.sixth.getBeginweek(), week.Wed.sixth.getEndweek());
		ReadDB.reading(getActivity(), b74,29);
		db.updateTitle(34, 34, week.Wed.senventh.getName(),
				week.Wed.senventh.getClassroom(), "######",
				week.Wed.senventh.getTeacher(), week.Wed.senventh.getWeekday(),week.Wed.senventh.getBeginweek(), week.Wed.senventh.getEndweek());
		ReadDB.reading(getActivity(), b84,34);
		db.updateTitle(39, 39, week.Wed.eighth.getName(),
				week.Wed.eighth.getClassroom(), "######",
				week.Wed.eighth.getTeacher(), week.Wed.eighth.getWeekday(),week.Wed.eighth.getBeginweek(), week.Wed.eighth.getEndweek());
		ReadDB.reading(getActivity(), b94,39);

		db.updateTitle(5, 5, week.Thus.first.getName(),
				week.Thus.first.getClassroom(), "######",
				week.Thus.first.getTeacher(), week.Thus.first.getWeekday(),week.Thus.first.getBeginweek(), week.Thus.first.getEndweek());
		ReadDB.reading(getActivity(), b25,5);
		db.updateTitle(10, 10, week.Thus.second.getName(),
				week.Thus.second.getClassroom(), "######",
				week.Thus.second.getTeacher(), week.Thus.second.getWeekday(),week.Thus.second.getBeginweek(), week.Thus.second.getEndweek());
		ReadDB.reading(getActivity(), b35,10);
		db.updateTitle(15, 15, week.Thus.third.getName(),
				week.Thus.third.getClassroom(), "######",
				week.Thus.third.getTeacher(), week.Thus.third.getWeekday(),week.Thus.third.getBeginweek(), week.Thus.third.getEndweek());
		ReadDB.reading(getActivity(), b45,15);
		db.updateTitle(20, 20, week.Thus.forth.getName(),
				week.Thus.forth.getClassroom(), "######",
				week.Thus.forth.getTeacher(), week.Thus.forth.getWeekday(),week.Thus.forth.getBeginweek(), week.Thus.forth.getEndweek());
		ReadDB.reading(getActivity(), b55,20);
		db.updateTitle(25, 25, week.Thus.fifth.getName(),
				week.Thus.fifth.getClassroom(), "######",
				week.Thus.fifth.getTeacher(), week.Thus.fifth.getWeekday(),week.Thus.fifth.getBeginweek(), week.Thus.fifth.getEndweek());
		ReadDB.reading(getActivity(), b65,25);
		db.updateTitle(30, 30, week.Thus.sixth.getName(),
				week.Thus.sixth.getClassroom(), "######",
				week.Thus.sixth.getTeacher(), week.Thus.sixth.getWeekday(),week.Thus.sixth.getBeginweek(), week.Thus.sixth.getEndweek());
		ReadDB.reading(getActivity(), b75,30);
		db.updateTitle(35, 35, week.Thus.senventh.getName(),
				week.Thus.senventh.getClassroom(), "######",
				week.Thus.senventh.getTeacher(), week.Thus.senventh.getWeekday(),week.Thus.senventh.getBeginweek(), week.Thus.senventh.getEndweek());
		ReadDB.reading(getActivity(), b85,35);
		db.updateTitle(40, 40, week.Thus.eighth.getName(),
				week.Thus.eighth.getClassroom(), "######",
				week.Thus.eighth.getTeacher(), week.Thus.eighth.getWeekday(),week.Thus.eighth.getBeginweek(), week.Thus.eighth.getEndweek());
		ReadDB.reading(getActivity(), b95,40);

		db.updateTitle(6, 6, week.Fri.first.getName(),
				week.Fri.first.getClassroom(), "######",
				week.Fri.first.getTeacher(), week.Fri.first.getWeekday(),week.Fri.first.getBeginweek(), week.Fri.first.getEndweek());
		ReadDB.reading(getActivity(), b26,6);
		db.updateTitle(11, 11, week.Fri.second.getName(),
				week.Fri.second.getClassroom(), "######",
				week.Fri.second.getTeacher(), week.Fri.second.getWeekday(),week.Fri.second.getBeginweek(), week.Fri.second.getEndweek());
		ReadDB.reading(getActivity(), b36,11);
		db.updateTitle(16, 16, week.Fri.third.getName(),
				week.Fri.third.getClassroom(), "######",
				week.Fri.third.getTeacher(), week.Fri.third.getWeekday(),week.Fri.third.getBeginweek(), week.Fri.third.getEndweek());
		ReadDB.reading(getActivity(), b46,16);
		db.updateTitle(21, 21, week.Fri.forth.getName(),
				week.Fri.forth.getClassroom(), "######",
				week.Fri.forth.getTeacher(), week.Fri.forth.getWeekday(),week.Fri.forth.getBeginweek(), week.Fri.forth.getEndweek());
		ReadDB.reading(getActivity(), b56,21);
		db.updateTitle(26, 26, week.Fri.fifth.getName(),
				week.Fri.fifth.getClassroom(), "######",
				week.Fri.fifth.getTeacher(), week.Fri.fifth.getWeekday(),week.Fri.fifth.getBeginweek(), week.Fri.fifth.getEndweek());
		ReadDB.reading(getActivity(), b66,26);
		db.updateTitle(31, 31, week.Fri.sixth.getName(),
				week.Fri.sixth.getClassroom(), "######",
				week.Fri.sixth.getTeacher(), week.Fri.sixth.getWeekday(),week.Fri.sixth.getBeginweek(), week.Fri.sixth.getEndweek());
		ReadDB.reading(getActivity(), b76,31);
		db.updateTitle(36, 36, week.Fri.senventh.getName(),
				week.Fri.senventh.getClassroom(), "######",
				week.Fri.senventh.getTeacher(), week.Fri.senventh.getWeekday(),week.Fri.senventh.getBeginweek(), week.Fri.senventh.getEndweek());
		ReadDB.reading(getActivity(), b86,36);
		db.updateTitle(41, 41, week.Fri.eighth.getName(),
				week.Fri.eighth.getClassroom(), "######",
				week.Fri.eighth.getTeacher(), week.Fri.eighth.getWeekday(),week.Fri.eighth.getBeginweek(), week.Fri.eighth.getEndweek());
		ReadDB.reading(getActivity(), b96,41);
	}

}