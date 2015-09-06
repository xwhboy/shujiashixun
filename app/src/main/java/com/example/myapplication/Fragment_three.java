package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public class Fragment_three extends Fragment {

    public final String[] year = {"2013", "2014", "2015", "2016"};
    public final String[] examType = {"期中", "期末"};
    private String yearSelected;
    private String examTypeSelected;
    private ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> name = new ArrayList<String>();
    private Person person;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //在这里定义Fragment的布局
        View rootView = inflater.inflate(R.layout.fragment_score,
                container, false);
        final Person person = new Person();
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        this.adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, name);
        listView.setAdapter(adapter);
        Spinner yearSpinner = (Spinner) rootView.findViewById(R.id.yearSpinner);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, year);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        Spinner tuitorSpinner = (Spinner) rootView.findViewById(R.id.tuitorSpinner);
        ArrayAdapter<String> tuitorAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, examType);
        tuitorSpinner.setAdapter(tuitorAdapter);
        tuitorSpinner.setOnItemSelectedListener(new ExamTypeSelctedListener());
        Button button = (Button) rootView.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("UserType",person.getUserType());
                if(person._userType.equals("Teacher"))
                    getTeacherGrade();
                else
                {getGrade();}
                adapter.notifyDataSetChanged();
            }
        });


        return rootView;
    }


    public void getTeacherGrade()
    {
        Log.i("Teacher Grade getted",person._userType);
        String gradeString = Data.grade(person._ID, examTypeSelected, yearSelected);
        Log.i("The grade String is ", gradeString);
        List<StudentGrade> gradeList = DataParser.getContentIntoStudentGradeList(gradeString);
//        ArrayList<String> nameList = new ArrayList<String>();
//        ArrayList<String> gradeArrayList = new ArrayList<String>();
//        ArrayList<String> IDList = new ArrayList<String>();
//        ArrayList<String> courseNameList = new ArrayList<String>();

            Toast.makeText(this.getActivity(),"成绩已更新",Toast.LENGTH_SHORT);

        this.name.clear();
        for (int i = 0; i < gradeList.size(); i++) {
            this.name.add(gradeList.get(i).getStudentName() + ":" + gradeList.get(i).getStudentID() + ":" + gradeList.get(i).getCourseName() +":" + gradeList.get(i).getScore());
        }
    }

    public void getGrade() {

        Person aperson = new Person();
        String gradeString = Data.grade(aperson.getID(), examTypeSelected, yearSelected);
        Log.i("The grade String is ", gradeString);

        List<gradeClass> gradeList = DataParser.getContentIntoGradeList(gradeString);
        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> gradeArrayList = new ArrayList<String>();
        this.name.clear();
        for (int i = 0; i < gradeList.size(); i++) {
            this.name.add(gradeList.get(i).getName() + ":" + gradeList.get(i).getGrade());
        }

    }


    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            yearSelected = year[arg2];
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
       }

        class ExamTypeSelctedListener implements AdapterView.OnItemSelectedListener {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                       long arg3) {
                examTypeSelected = String.valueOf(arg2+1);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }

        }

}