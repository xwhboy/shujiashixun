package com.example.myapplication;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by нд╨ф on 2015/8/12.
 */

class Hk_net {

    private String getData="UNDO";
    public String  get_NetMessage(){

          //getData=refresh(String id, String lastTime)

          return getData;
    }



    public boolean send_hk_message(String id, String title, String content){
           boolean if_send=false;
           //if_send=post(String id, String title, String content)
           return if_send;
    }


    public void analysis(){
        getData=get_NetMessage();
        if(getData=="UNDO"){
            Log.v("Error","Error");
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("author", "author");
        map.put("title", "title");
        map.put("time", "time");
        map.put("content", "content");
        map.put("number", "number");


        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("author", "author1");
        map1.put("title", "title1");
        map1.put("time", "time1");
        map1.put("content", "content1");
        map1.put("number", "number1");



        List<Map> list = new ArrayList<Map>();
        list.add(map1);
        list.add(map);
        Gson gson2 = new Gson();
        String str2 = gson2.toJson(list);
        System.out.println(str2);


        Gson jgson = new Gson();
        List<Hk_JsonType> hks = jgson.fromJson(str2, new TypeToken<List<Hk_JsonType>>(){}.getType());
        for(int i = 0; i < hks.size() ; i++)
        {
            Hk_JsonType p = hks.get(i);
            String a=p.getJauthor();
            System.out.println(a);
            String b=p.getJtitle();
            System.out.println(b);
            String c=p.getJtime();
            System.out.println(c);
            String e=p.getJcontent();
            System.out.println(e);
            String f=p.getJnumber();
            System.out.println(f);

        }



//        {"map1":"{\"author\":\"author\",\"title\":\"title\",\"author\":\"author\",\"content\":\"content\",\"number\":\"number\"}","map":"{\"author\":\"author\",\"title\":\"title\",\"author\":\"author\",\"content\":\"content\",\"number\":\"number\"}"}


    }

}