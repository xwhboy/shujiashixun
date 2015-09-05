package com.example.myapplication;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by EricCheung on 8/28/15.
 */


//注意事项
//Don't access the variable inside this class directly, use get/set method to reach them;
//To retrieve class of the person, using getStudentClass() method instead of getClass() method
public class Person {

       public static String _name;//名字
        public static String _gender;//性别
       public static String _grade;//年级
       public static String _class;//班级
       public static String _userType;//用户类别
       public static boolean _newUserFlag;//是否新用户的
       public static String _ID;//用户ID
        private static Person person = null;

    public String getRelationShip() {
        return _relationShip;
    }

    public void setRelationShip(String _relationShip) {
        this._relationShip = _relationShip;
    }

    protected String _relationShip;

    public String getAssumptionDate() {
        return _assumptionDate;
    }

    public void setAssumptionDate(String _assumptionDate) {
        this._assumptionDate = _assumptionDate;
    }

    protected String _assumptionDate;

    //Initilization method for student;
    public void StudentPerson(String name, String gender, String grade, String userClass, String userType, boolean newUserFlag, String ID)
    {

        this._name = new String(name);
        this._gender = new String(gender);
        this._grade = new String(grade);
        this._class = new String(userClass);
        this._userType = new String(userType);
        this._newUserFlag = newUserFlag;
        this._ID = new String(ID);
//        return this;
    }

//Initialization method for student
//    public Person Person(String name, String gender,String grade,String _class)
//    {
//        this._name = new String(name);
//        this._gender = new String(gender);
//        this._grade = new String(grade);
//        this._class = new String(_class);
//        return this;
//    }

    public static Person getInstance()
    {
        if(person == null)
        {
            Log.i("Error","Initializa the person class first");
            return null;
        }
        else {
            return person;
        }
    }
    public boolean Person()
    {
        Log.i("Warning","use the overrided initialization method");
        return false;
    }
    //Initialization method for teacher
    public Person TeacherPerson(String name, String gender, String assumptionDate)
    {
        this._name =new String(name);
        this._gender = new String(gender);
        this._assumptionDate = new String(assumptionDate);
        this._userType = new String("Teacher");

        return this;
    }

    //Initialization method for patriarch
    public Person PatriarchPerson(String name, String relation)
    {

        this._name = new String(name);
        this._relationShip = new String(relation);
        this._userType = new String("Patriach");
        return this;
    }

    public String getName()
    {
        return this._name;
    }
    public boolean setName(String name)
    {
        if(this._name != null || this._name.length() <=0)
        {
         this._name = name;
            return true;
        }
        else
            return false;
    }
    public String getGender( )
    {
        return this._gender;
    }
    public boolean setGender(String gender)
    {
        if(this._gender != null || this._gender.length() <=0)
        {
            this._gender = gender;
            return true;
        }
        else
            return false;
    }

    public String getGrade( )
    {
        return this._grade;
    }
    public boolean setGrade(String grade)
    {
        if(this._grade != null || this._grade.length() <=0)
        {
            this._grade = grade;
            return true;
        }
        else
            return false;
    }


    public String getUserClass()
    {
        return this._class;
    }
    public boolean setUserClass(String text)
    {
        if(this._class != null || this._class.length() <=0)
        {
            this._class = text;
            return true;
        }
        else
            return false;
    }


    public String getID()
    {
        return this._ID;
    }
    public boolean setID(String text)
    {

            this._ID = new String(text);
            return true;

    }


    public String getUserType()
    {
        return this._userType;
    }
    public boolean setUserType(String text)
    {
        if(this._userType != null || this._userType.length() <=0)
        {
            this._userType = text;
            return true;
        }
        else
            return false;
    }



    public boolean getNewUserFlag()
    {
        return this._newUserFlag;
    }
    public boolean setNewUserFlag(boolean text)
    {

            this._newUserFlag = text;
            return true;
    }

}
