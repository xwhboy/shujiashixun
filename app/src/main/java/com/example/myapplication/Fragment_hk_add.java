package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by �ĺ� on 2015/8/12.
 */
public class Fragment_hk_add extends Activity {
    public Hk_net net2=new Hk_net();
    private EditText sendTitle;
    private EditText sendContent;
    private Button sendMes;
    private boolean cherk_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_hk_add);

        sendMes=(Button)findViewById(R.id.sendmessage);
        sendContent=(EditText)findViewById(R.id.HK_add_content);
        sendTitle=(EditText)findViewById(R.id.HK_add_title);

        setListener();

    }
    private void setListener(){
        sendMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String send_title=sendTitle.getText().toString();
                String send_content=sendContent.getText().toString();
                String id="2201330614";
                cherk_send=net2.send_hk_message(id,send_title,send_content);
                Log.v("fasong",cherk_send+"");

                if(cherk_send){
                    Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT);

                    finish();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "发送失败", Toast.LENGTH_SHORT);
                }
            }
        });
    }

}
