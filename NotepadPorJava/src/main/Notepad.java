package main;
import java.io.*;
public class Notepad {

	public static void main(String[] args) {
		ProcessBuilder pb=new ProcessBuilder("Notepad");
		try {
			Process p=pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
