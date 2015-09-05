package com.example.myapplication;

import java.io.Serializable;
import java.util.List;



public class Week{
	
	public Day Mon;
	public Day Tues;
	public Day Wed;
	public Day Thus;
	public Day Fri;
	
//	public Week(){
//		Mon = new Day("��һ");
//		Tue = new Day("�ܶ�");
//		Wed = new Day("����");
//		Thu = new Day("����");
//		Fri = new Day("����");
//	}
	public Week(){
		Mon = new Day();
		Tues = new Day();
		Wed = new Day();
		Thus = new Day();
		Fri = new Day();
	}
}
