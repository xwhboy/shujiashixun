package com.example.myapplication;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class Detail extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		this.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);


		final EditText kcmEditText = (EditText) findViewById(R.id.kcm_edit);
		final EditText addressEditText = (EditText) findViewById(R.id.address_edit);
		final EditText teacherNameEditText = (EditText) findViewById(R.id.teachername_edit);
		final EditText courseNumEditText = (EditText) findViewById(R.id.haoma_edit);
		final EditText weekdayEditText = (EditText) findViewById(R.id.weekday_edit);
		final EditText beginweekEditText = (EditText) findViewById(R.id.beginweek_edit);
		final EditText endweekEditText = (EditText) findViewById(R.id.endweek_edit);

		TextView back = (TextView) findViewById(R.id.back);

		Bundle b = getIntent().getExtras();
		int e = b.getInt("key");
		Cursor cursor = null;
		DBAdapter db = new DBAdapter(Detail.this);
		db.open();
		cursor = db.getAllTitles();
		cursor.moveToFirst();

		while (cursor.moveToNext()) {
			int m = cursor.getInt(cursor.getColumnIndex("myid"));
			if (m == e) {
				kcmEditText.setText(cursor.getString(cursor
						.getColumnIndex("classname")));
				addressEditText.setText(cursor.getString(cursor
						.getColumnIndex("address")));
				courseNumEditText.setText(cursor.getString(cursor
						.getColumnIndex("tel")));
				teacherNameEditText.setText(cursor.getString(cursor
						.getColumnIndex("teachername")));
				weekdayEditText.setText(cursor.getString(cursor
						.getColumnIndex("weekday")));
				beginweekEditText.setText(cursor.getString(cursor
						.getColumnIndex("beginweek")));
				endweekEditText.setText(cursor.getString(cursor
						.getColumnIndex("endweek")));

			}

		}

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				Intent intent = new Intent(detail.this, MainActivity.class);
//				startActivity(intent);
				Detail.this.finish();
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			Detail.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

}
