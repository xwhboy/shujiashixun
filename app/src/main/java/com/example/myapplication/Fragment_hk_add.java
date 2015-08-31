package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

                String send_title = sendTitle.getText().toString();
                String send_content = sendContent.getText().toString();
                String id = "2201330614";
                cherk_send = net2.send_hk_message(id, send_title, send_content);
                Log.v("fasong", cherk_send + "");

                if (cherk_send) {
                   dialog1();

                } else {
                   dialog2();
                }
            }
        });
    }
    private void dialog1(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("发送成功"); //设置标题
        builder.setMessage("发送成功，作业已同步"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Fragment_hk_add.this.finish();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }
    private void dialog2(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("发送失败"); //设置标题
        builder.setMessage("发送失败?"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }
}
