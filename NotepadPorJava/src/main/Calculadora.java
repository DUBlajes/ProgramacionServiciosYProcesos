package main;
import java.io.*;
public class Calculadora {

	public static void main(String[] args) {
		ProcessBuilder pb=new ProcessBuilder("Calc");
		try {
			Process p=pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
