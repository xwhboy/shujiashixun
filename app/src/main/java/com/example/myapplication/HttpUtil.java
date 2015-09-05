package com.example.myapplication;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtil {
    static HttpURLConnection connection = null;
    static String response = "";

    static String getResponse(final String request,final String servlet) {
        response = "";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://1092300134zk.nat123.net/Db/" + servlet);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setUseCaches(false);

                    OutputStream out = new BufferedOutputStream(connection.getOutputStream());
//        request = URLEncoder.encode(request, "utf-8");
                    out.write(request.getBytes());
                    out.flush();
                    out.close();

                    response = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    connection.disconnect();
                }
            }
        }).start();

        //阻塞线程 等待传�?
        int i = 0;
        while((response == "") && (i < 9))
        {
            try {
                i++;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        return response;
    }
}
