package com.example.myapplication;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by �ĺ� on 2015/8/12.
 */

class Hk_net {


    private String [][]dataGroup;
    private String getData="UNDO";

    public String  get_NetMessage(String stime){
          String getdata0="";
          String setid="";
          System.out.print("_____________________________________________");
          System.out.print(stime);
          setid=getid();

          getdata0=Data.refresh(setid, stime);

          return getdata0;
    }



    public boolean send_hk_message(String id, String title, String content){
           boolean if_send=false;
           if_send=Data.post(id, title, content);
           return if_send;
    }


    public int analysis(String stime){
        getData=get_NetMessage(stime);

        int k = 0;
        while((getData == "") && (k < 9))
        {
            try {
                k++;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(getData==""){
            return -1;
        }else{
            Gson jgson = new Gson();
            List<Hk_JsonType> hks = jgson.fromJson(getData, new TypeToken<List<Hk_JsonType>>(){}.getType());
            int datanum=-1;
            datanum=hks.size();
            if(datanum==0){
                Log.v("no","Nothing return !");
                return datanum;
            }
            else{
                dataGroup=new String[datanum][4];
                for(int i = 0; i < hks.size() ; i++)
                {
                    Hk_JsonType p = hks.get(i);
                    dataGroup[i][0]=p.getJauthor();
                    System.out.println(dataGroup[i][0]);
                    dataGroup[i][1]=p.getJtitle();
                    System.out.println(dataGroup[i][1]);
                    dataGroup[i][2]=p.getJtime();
                    System.out.println(dataGroup[i][2]);
                    dataGroup[i][3]=p.getJcontent();
                    System.out.println(dataGroup[i][3]);


                }
            }

            return datanum;
        }


    }
    public String[][] getdataGroup(){
        return dataGroup;
    }

    private String getid() {
        String user_id = "";
        user_id="1201330614";
        return user_id;
    }
}