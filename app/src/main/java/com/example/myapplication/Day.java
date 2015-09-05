package com.example.myapplication;

import java.io.Serializable;

public class Day implements Serializable{
	
	public course first;
	public course second;
	public course third;
	public course forth;
	public course fifth;
	public course sixth;
	public course senventh;
	public course eighth;

//	public Day(String day){
//		first = new course(day);
//		second = new course(day);
//		third = new course(day);
//		forth = new course(day);
//		fifth = new course(day);
//		sixth = new course(day);
//		senventh = new course(day);
//		eighth = new course(day);
//	}
	public Day(){
		first = new course();
		second = new course();
		third = new course();
		forth = new course();
		fifth = new course();
		sixth = new course();
		senventh = new course();
		eighth = new course();
	}
}
