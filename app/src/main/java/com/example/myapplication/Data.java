package com.example.myapplication;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static String login(String type, String id, String pw) {
        // 创建键值对链表，存储登录所发送方的信息
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("type", type));
        nvp.add(new BasicNameValuePair("id", id));
        nvp.add(new BasicNameValuePair("pw", pw));
        String input = URLEncodedUtils.format(nvp, "utf-8");
        // 发送登录信息，服务器收到后进行登录验证，返回布尔值作为登录是否成功的结果
        return HttpUtil.getResponse(input, "Login");
    }

    public static boolean change(String id, String oldPw, String newPw) {
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("id", id));
        nvp.add(new BasicNameValuePair("oldPw", oldPw));
        nvp.add(new BasicNameValuePair("newPw", newPw));
        String input = URLEncodedUtils.format(nvp, "utf-8");
        return Boolean.valueOf(HttpUtil.getResponse(input, "Change"));
    }

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

    public static String course(String id) {
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("id", id));
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
