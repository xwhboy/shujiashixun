package com.example.myapplication;

import android.content.Context;

public class Insert {
	public void inserting(Context context, String s1, String s2, String s3,
			String s4, String s5, String s6, String s7) {
		DBAdapter db = new DBAdapter(context);
		db.inserttable(1, s1, s2, s3, s4, s5, s6, s7);

	}
}
