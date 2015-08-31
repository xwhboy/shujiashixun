package com.example.myapplication;

import com.example.myapplication.His_cache;
import com.example.myapplication.Hk_net;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_hk extends Fragment implements SwipeRefreshLayout.OnRefreshListener  {

    //布局变量
    private ListView listView;
    private TextView ui_ti;
    private TextView ui_con;
    private TextView ui_data;
    private TextView ui_tec;
    private Button  ui_add;
    private List<Map<String, Object>> listems;
    private SimpleAdapter adapter;
    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mSwipeLayout;
    //定义数据

     String  data2[][]={{"数学作业","大ssss，大ssss，大ssss，重要的事情说三遍、","张老师","2015.08.12","20150812"},
            {"语文作业","大ssss大ssss大ssss","李老师","2015.08.12","20150812"}};

    //作业号
    private int hk_id;
    //作业数
    private  int hk_num;
    //文件数组
    private String hk_data[][];
    //标题
    private String hk_ti;
    //内容
    private  String hk_con;
    //老师名字
    private  String hk_tec;

    private String path;
    private  int zuijia=0;
    //测试
    His_cache his=new His_cache();
    Hk_net net=new Hk_net();
    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {

                case REFRESH_COMPLETE:
//                    add_Item(data2, 2);
//                    his.set_his(data2, 2);
//                    String str[][]=his.get_his();
//                    int zuijia=his.get_his_num();
//                    Log.v("测试",kk);

                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        };
    };
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		//在这里定义Fragment的布局
		View rootView = inflater.inflate(R.layout.fragment_hk,
				container, false);

        //findview
        listView=(ListView)rootView.findViewById(R.id.hk_listview);
        ui_ti=(TextView)rootView.findViewById(R.id.hk_title);
        ui_con=(TextView)rootView.findViewById(R.id.hk_content);
        ui_data=(TextView)rootView.findViewById(R.id.hk_data);
        ui_tec=(TextView)rootView.findViewById(R.id.hk_teacher);
        ui_add=(Button)rootView.findViewById(R.id.HK_add);


        //初始化xml文本，置0



        //test

        final String  data[][]={{"数学作业","陈文发大傻逼，陈文发大傻逼，陈文发大傻逼，重要的事情说三遍、","张老师","2015.08.12","20150812"},
                {"语文作业","XXXXXXXXXXXXXXX","李老师","2015.08.12","20150812"}};

        mSwipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.id_swipe_ly);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        String str[][]=his.get_his();
        if(str[0][0]=="ERROR"){

            Toast.makeText(this.getActivity(), "请刷新", Toast.LENGTH_SHORT).show();
        }
        else {
            zuijia = his.get_his_num();

        }
        setAdapter(str, zuijia);

        ui_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if判断用户
                Intent intent=new Intent(getActivity(),Fragment_hk_add.class);
                getActivity().startActivity(intent);
            }
        });


		return rootView;
	}

    public void onRefresh()
    {
        // Log.e("xxx", Thread.currentThread().getName());



        new Thread(){
            public void run(){



                Log.e("xxx", "网络接收测试");
            }
        }.start();

        net.analysis();
        add_Item(data2, 2);
        his.set_his(data2, 2);
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

    }

    public void  setAdapter(String da[][],int num){
        listems  = new ArrayList<Map<String, Object>>();
        for(int i = 0; i <num; i++) {
            Map<String, Object> listem  = new HashMap<String, Object>();
            listem.put("title", da[i][0]);
            listem .put("content", da[i][1]);
            listem.put("teacher", da[i][2]);
            listem.put("data", da[i][3]);
            listems .add(listem);
        }

         adapter = new SimpleAdapter(this.getActivity().getApplicationContext(),
                listems,R.layout.hk_item,
                new String[]{"title","content","teacher","data"},
                new int[]{R.id.hk_title,R.id.hk_content,R.id.hk_teacher, R.id.hk_data});

        listView.setAdapter(adapter);

    }


    public void add_Item(String da[][],int num){

        //更新得是同一个listitem，不能new
        for(int i = 0; i <num; i++) {
            Map<String, Object> listem2  = new HashMap<String, Object>();
            listem2 .put("title", da[i][0]);
            listem2 .put("content", da[i][1]);
            listem2.put("teacher", da[i][2]);
            listem2 .put("data", da[i][3]);
            listems .add(listem2);

            adapter.notifyDataSetChanged();
    //获得新数据后得修改原来的data，data存储

        }


        Toast.makeText(this.getActivity(), "作业已更新", Toast.LENGTH_SHORT).show();


    }

    //本地预缓存id


    //本地缓存id
    private void set_hk_cache(int id){
        String ids=id+"";
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("id_store", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("hk_id",ids);
        //将数据保存在文件中
        editor.commit();
    }
    //获取本地缓存id
    private int get_hk_cache() {
        int id = 0;
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("id_store", Activity.MODE_PRIVATE);
        String ids = sharedPreferences1.getString("hk_id", "");
        id=Integer.parseInt(ids);
        Toast.makeText(this.getActivity(), "hk_id" + id, Toast.LENGTH_LONG).show();
        return id;
    }
    private void set_hk_his(String hisdata[][]){
        String name=hisdata[0][3];
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("id_store", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("hk_his",name);
        //将数据保存在文件中
        editor.commit();
    }
    public void ini_data(){

        hk_id=get_hk_cache();
        send_id(hk_id);
        hk_num=get_data(0);

    }


    public void send_id(int id){

    }

    public int  get_data(int ch){

        int num=999;
        num=analysis_data_num();
        if(num==0)
        {
            if(ch==0)
            {
                //初始化时没有今日的作业
                Toast.makeText(this.getActivity(), "今日无作业", Toast.LENGTH_LONG).show();
            }
            else {
                //刷新时不增加
            }
        }
        else
        {
            //String[][0]是title String[][1]是content String[][2]是teacher String[][3]是日期 String [][4]是hk_id
            hk_data=new String[num][5];
            analysis_data();
        }
        return num;
    }

    //数据解析
    public int analysis_data_num(){
        int num=0;



        return num;
    }
    //字符解析
    public void analysis_data(){

    }


}