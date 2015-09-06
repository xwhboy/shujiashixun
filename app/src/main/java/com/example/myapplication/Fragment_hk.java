package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.widget.SwipeRefreshLayout;

import java.text.SimpleDateFormat;
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
    //定义数据p
    private String usertype;
    private boolean ifthefirst=false;
    private String userid;
     String dataItem[][];

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
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {

                case REFRESH_COMPLETE:


                    if(dataItem==null){
                        Log.v("fuck", "fuck");
                        getnull();
                        mSwipeLayout.setRefreshing(false);
                        break;
                    }
                    else {
                        add_Item(dataItem, hk_num);
                        mSwipeLayout.setRefreshing(false);

                        break;
                    }

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
        ifthefirst=Person._newUserFlag;
        if (ifthefirst) {
            set_hk_cache("2000-09-01 00:00:00");
        }


        usertype=Person._userType;
        userid=Person._ID;

        System.out.println("--------------------------------------------");
        System.out.println(usertype + "            " + userid + "            " + ifthefirst);
        //test



        mSwipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.id_swipe_ly);


        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(this);
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
                if(usertype.equals("Teacher")) {
                    Intent intent = new Intent(getActivity(), Fragment_hk_add.class);
                    getActivity().startActivity(intent);
                }
                else{
                    Toast.makeText(Fragment_hk.this.getActivity(), "没有访问权限", Toast.LENGTH_SHORT).show();
                }
            }
        });




		return rootView;
	}

    public void onRefresh()
    {
        // Log.e("xxx", Thread.currentThread().getName());
        String getcache=get_hk_cache();
        hk_num=net.analysis(getcache);
        if(hk_num<=0){
            dataItem=null;
        }
        else{
            dataItem=new String[hk_num][4];
            dataItem=net.getdataGroup();
            Log.v("dataItem", dataItem[0][0]);
            his.set_his(dataItem, hk_num);



        }
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

    }

    public void  setAdapter(String da[][],int num){
        listems  = new ArrayList<Map<String, Object>>();
        for(int i = 0; i <num; i++) {
            Map<String, Object> listem  = new HashMap<String, Object>();
            listem.put("title", da[i][1]);
            listem .put("content", da[i][3]);
            listem.put("teacher", da[i][0]);
            String st0=da[i][2].substring(0,11);
            listem.put("data", st0);
            listems .add(0,listem);
        }

         adapter = new SimpleAdapter(this.getActivity().getApplicationContext(),
                listems,R.layout.hk_item,
                new String[]{"title","content","teacher","data"},
                new int[]{R.id.hk_title,R.id.hk_content,R.id.hk_teacher, R.id.hk_data});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Adapter adapter=parent.getAdapter();
//                获取listview中的Adapter对象，包括所有item
                Map<String,String> map=(Map<String, String>) adapter.getItem(position);
                newdialog(map.get("teacher")+"老师: "+map.get("title"),"内容 : "+map.get("content"));
            }
        });
    }


    public void add_Item(String da[][],int num){

        //更新得是同一个listitem，不能new
        for(int i = 0; i <num; i++) {
            Map<String, Object> listem2  = new HashMap<String, Object>();
            listem2 .put("title", da[i][1]);
            listem2 .put("content", da[i][3]);
            listem2.put("teacher", da[i][0]);
            String st0=da[i][2].substring(0,11);
            listem2.put("data",st0);
            listems .add(0,listem2);

            adapter.notifyDataSetChanged();
    //获得新数据后得修改原来的data，data存储

        }
        System.out.println(num);
//        set_hk_cache(da[num-1][2]);
        Toast.makeText(this.getActivity(), "作业已更新", Toast.LENGTH_SHORT).show();
        set_hk_cache(da[num - 1][2]);

    }
    public void  getnull(){
        Toast.makeText(this.getActivity(), "无最新作业", Toast.LENGTH_SHORT).show();

    }
    private void newdialog(String tn,String cn){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this.getActivity());  //先得到构造器
        builder.setTitle(tn); //设置标题
        builder.setMessage(cn); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }




    //本地缓存id
    private void set_hk_cache(String time){

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("id_store", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("hk_id",time);
        //将数据保存在文件中
        editor.commit();
    }



    //获取本地缓存id
    public String get_hk_cache() {
        String time="";
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("id_store", Activity.MODE_PRIVATE);
        time = sharedPreferences1.getString("hk_id", "");
        Log.v("本地缓存",time);
        return time;
    }

}