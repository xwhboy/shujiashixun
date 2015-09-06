package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;


public class modifyPasswordActivity extends Activity {

    private int userType;
    private String ID;
    private EditText IDEditText;
    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        Button button2 = (Button)findViewById(R.id.button2);
         IDEditText = (EditText)findViewById(R.id.IDEditText);
         oldPasswordEditText = (EditText)findViewById(R.id.oldPasswordEditText);
         newPasswordEditText = (EditText)findViewById(R.id.newPasswordEditText);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    modifyPassword();
            }
        });

        RadioButton studentButton = (RadioButton)findViewById(R.id.studentButton);
        studentButton.setChecked(true);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                userType = radioGroup.getCheckedRadioButtonId();
            }
        });

    }

    private void modifyPassword()
    {
        ID = IDEditText.getText().toString();
        Log.i("ID:",ID);

        switch(userType)
        {
            case R.id.studentButton:
                ID = "1" + ID;
                break;
            case R.id.teacherButton:
                ID = "2" + ID;
                break;
            case R.id.patriarchButton:
                ID = "3" + ID;
                break;
        }
        Log.i("Old Password:",oldPasswordEditText.getText().toString());
        Log.i("New Password:",newPasswordEditText.getText().toString());
      boolean flag =  Data.change(ID,oldPasswordEditText.getText().toString(),newPasswordEditText.getText().toString());
        if(flag == true)
            Toast.makeText(getApplicationContext(),"修改成功，请重新登陆",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"修改失败，请重试或重新登录",Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
