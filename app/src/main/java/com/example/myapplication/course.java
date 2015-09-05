package com.example.myapplication;

import java.io.Serializable;

public class course implements Serializable{
	private String name;
	private String teacher ;
	private String weekday;
	private String beginweek ;
	private String endweek ;
	private String classroom ;
	
//	public course(String day){
//		name = "����";
//		teacher = "���Һ�";
//		
//		weekday = day;
//		beginweek = "1";
//		endweek = "20";
//		classroom = "A2-202";
//		
//	}
	public course(){
		
	}
	
	public String getName(){
		return name;
	}
	
	public String getTeacher(){
		return teacher;
	}
	 
	
	
	public String getWeekday(){
		return weekday;
	}
	
	public String getBeginweek(){
		return beginweek;
	}
	
	public String getEndweek(){
		return endweek;
	}
	
	public String getClassroom(){
		return classroom;
	}
}
