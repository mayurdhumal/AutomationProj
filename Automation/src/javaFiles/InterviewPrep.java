package javaFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InterviewPrep {
	
	static String text = "I use selenium webdriver, selenium is a tool for web applications automation.";
	static BufferedReader reader;
	static FileReader fileread;
	static StringBuffer strb;
	
	public static void reverseText(){
		/*Reverse a string using StringBuffer, reverse function*/
		String reverse = new StringBuffer(text).reverse().toString();
		System.out.println(reverse);
	}

	public static void reverseChar(){
		/*Reverse a string without using reverse function*/
		strb = new StringBuffer();
		
		for(int i = text.length()-1; i >= 0; i-- ){
			strb = strb.append(text.charAt(i));
		}
		System.out.println(strb);
	}

	public static void replace(){
		/*Replace substring with another string in a string*/
		String toBeReplaced = "selenium";
		String replacedWith = "firefox";
		String[] StringArray = text.split(toBeReplaced);
		
		strb = new StringBuffer();
		
		for(int i= 0; i<=StringArray.length-1; i++ ){
			
			strb = strb.append(StringArray[i]);
			
			if (i != StringArray.length - 1) {
				strb = strb.append(replacedWith);
			}
		}
		System.out.println(strb);
	}
	
	public static void readFile() throws Exception{
		/*Program to read from file line by line: 
		 * bufferedReader class provides readLine method to be used to read the file line by line. */
		fileread = new FileReader("F:\\JavaProj\\test\\pom.xml");
		reader = new BufferedReader(fileread);
		strb = new StringBuffer();
		try{
			while (reader.readLine()!= null){
				strb.append(reader.readLine());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(strb);
	}
	
	
	public static void pattern(){
		for(int i=0; i<5; i++){
			for(int j=0; j<=i; j++){
				System.out.println(j);
			}	
			System.out.println(); //to print new line for each iteration of outer loop
		}
	}
	public static void main(String[] args) throws Exception {
		reverseText();
		reverseChar();
		replace();
		readFile();
		pattern();
	}

}
