package com.example.myapplication;

/**
 * Created by 文浩 on 2015/8/4.
 */
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import    java.text.SimpleDateFormat;
import java.util.Date;

public class His_cache {
    public String nowdate;
    public String get_id_time;
    public int getrow;
    public String  getTime(){
        SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd");
        SimpleDateFormat    formatter2    =   new    SimpleDateFormat    ("yyyyMMdd");

        nowdate    =    formatter.format(new    java.util.Date());
        get_id_time     =    formatter2.format(new    java.util.Date());
        Log.v("当前时间", nowdate);
        return nowdate;
    }




    public int get_his_num(){
        int kkk=getrow;
        return kkk;
    }
    public void  set_his(String data_w[][],int num) {
        String filename;
        filename = getTime()+".txt";

        String path = "/data/data/com.example.myapplication/".toString();
        File file = new File(path, filename);


        try {

            FileWriter out = new FileWriter(file, true);
            for(int i=0;i<num;i++)
            {
                for(int j=0;j<5;j++){
                    out.write(data_w[i][j]+"\t");
                }
                out.write("\r\n");
            }

            out.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public String[][] get_his(){

        int k=0;
        String shuchu[][];
        String jinggao[][]=new String[1][5];
        for(int i=0;i<5;i++){
            jinggao[0][i]="ERROR";
        }
        String temp_data[][]=new String[100][5];

        String filename = getTime()+".txt";
        BufferedReader instream;
        String path = "/data/data/com.example.myapplication/";
        String line; //一行数据
        File file = new File(path, filename);
        //
        if(!file.exists())
            {
                Log.v("-----测试------",jinggao[0][0]);
               return jinggao;
            }
        else {
            int row = 0; //逐行读取，并将每个数组放入到数组中
            try {
                instream = new BufferedReader(new FileReader(file));

                while ((line = instream.readLine()) != null) {
                    String[] temp = line.split("\t");
                    k = temp.length;
                    if (k > 0) {
                        for (int j = 0; j < 5; j++) {
                            temp_data[row][j] = temp[j];
                        }
                        row++;
                    } else {

                    }
                }
                getrow = row;
                shuchu = new String[row][5];
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < 5; j++) {
                        shuchu[i][j] = temp_data[i][j];
                    }
                }
                instream.close();
                return shuchu;
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        return jinggao;

    }


}
