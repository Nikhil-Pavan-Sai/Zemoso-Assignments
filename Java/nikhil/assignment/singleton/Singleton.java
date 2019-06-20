package nikhil.assignment.singleton;

import java.util.*;

public class Singleton{
  
	public String s; 

	public static Singleton getInstance(String str) 
    	{ 
		
		Singleton s1= new Singleton();
		s1.s=str;
	        return s1;
	}

	public void getStr(){
	
		System.out.println(s);
	}
}
