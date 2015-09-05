package com.example.myapplication;


/**
 * Created by EricCheung on 8/28/15.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


//Use getContentIntoArrayList method to get content into arraylist returned;
//method getContentIntoMap is the same.
public class DataParser {
    public static ArrayList<String> getContentIntoArrayList(String string)
    {
        ArrayList<String> temp = new ArrayList<>();
        Gson gson = new Gson();
        temp = gson.fromJson(string, new TypeToken<ArrayList<String>>(){}.getType());
        return temp;
    }
    public static Map<String,String> getContentIntoMap(String JSONString)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Map<String, String> map = gson.fromJson(JSONString, new TypeToken<Map<String, String>>() {}.getType());
        return map;
    }
    public static List<gradeClass> getContentIntoGradeList(String string)
    {
        Gson jgson = new Gson();
        List<gradeClass> hks = jgson.fromJson(string, new TypeToken<List<gradeClass>>(){}.getType());
        return hks;
    }
    public static List<StudentGrade> getContentIntoStudentGradeList(String string)
    {
        Gson jgson = new Gson();
        List<StudentGrade> hks = jgson.fromJson(string, new TypeToken<List<StudentGrade>>(){}.getType());
        return hks;
    }


}
