package nikhil.assignment.main;

import java.util.*;
import nikhil.assignment.data.JavaIncrAss1;
import nikhil.assignment.singleton.Singleton;

class MainClass{

	public static void main(String args[]){
	
		JavaIncrAss1 java = new JavaIncrAss1();
		java.getValues();
		//java.getLocalValues();
	
		Singleton single = Singleton.getInstance("Hey");
		
		single.getStr();
	}
}
