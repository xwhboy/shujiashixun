package com.example.myapplication;

public class Shared_File
{
	private String teacher;
	private String id;
	private String time;
	private String title;
	private String url;
	private String fileName;
	public Shared_File(){
		
	}
	
//	public Shared_File(String id,String time,String title){
//		this.id = id;
//		this.time = time;
//		this.title = title;
//	}
	public String getFilename()
	{
		return fileName;
	}
	public String getTeacher()
	{
		return teacher;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public String getId()
	{
		return id;
	}
	public String getTitle()
	{
		return title;
	}
	public String getTime()
	{
		return time;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public void setTitle(String title){
		this.title = title;
	}

}