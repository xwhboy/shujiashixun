package com.example.myapplication;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static boolean login(String id, String pw) {
        // 创建键�?对链表，存储登录�?��送方的信�?
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("id", id));
        nvp.add(new BasicNameValuePair("pw", pw));
        String input = URLEncodedUtils.format(nvp, "utf-8");
        // 发�?登录信息，服务器收到后进行登录验证，返回布尔值作为登录是否成功的结果
        return Boolean.valueOf(HttpUtil.getResponse(input, "Login"));
    }

    //是否�?��注册功能有待考究
//    public static boolean register(String id, String pw) {
//        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
//        nvp.add(new BasicNameValuePair("id", id));
//        nvp.add(new BasicNameValuePair("pw", pw));
//        String input = URLEncodedUtils.format(nvp, "utf-8");
//        return Boolean.valueOf(HttpUtil.getResponse(input, "Register"));
//    }

    public static String grade(String id) {
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("id", id));
        String input = URLEncodedUtils.format(nvp, "utf-8");
        return HttpUtil.getResponse(input, "Grade");
    }

    public static boolean post(String id, String title, String content) {
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("id", id));
        nvp.add(new BasicNameValuePair("title", title));
        nvp.add(new BasicNameValuePair("content", content));
        String input = URLEncodedUtils.format(nvp, "utf-8");
        return Boolean.valueOf(HttpUtil.getResponse(input, "Post"));
    }

    public static String refresh(String id, String lastTime) {
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("id", id));
        nvp.add(new BasicNameValuePair("lastTime", lastTime));
        String input = URLEncodedUtils.format(nvp, "utf-8");
        return HttpUtil.getResponse(input, "Refresh");
    }

    public static String course(String id, String grade, String classNo) {
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("id", id));
        nvp.add(new BasicNameValuePair("grade", grade));
        nvp.add(new BasicNameValuePair("classNo", classNo));
        String input = URLEncodedUtils.format(nvp, "utf-8");
        return HttpUtil.getResponse(input, "Course");
    }


    public static String checkUpdate(String id) {
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("id", id));
        String input = URLEncodedUtils.format(nvp, "utf-8");
        return HttpUtil.getResponse(input, "CheckUpdate");
    }

      //用于测试
//    public static String test(String id, String pw) {
//        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
//        nvp.add(new BasicNameValuePair("id", id));
//        nvp.add(new BasicNameValuePair("pw", pw));
//        String input = URLEncodedUtils.format(nvp, "utf-8");
//        return HttpUtil.getResponse(input, "Test");
//    }

}
