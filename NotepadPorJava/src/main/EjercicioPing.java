package main;
import java.io.*;
public class EjercicioPing {

	public static void main(String[] args) {
		ProcessBuilder pb=new ProcessBuilder("CMD","/C","ping 192.168.0.1"); 
		
		File fOut=new File("Salida.txt");
		File fError=new File("Error.txt");
		
		pb.redirectOutput(fOut);
		pb.redirectError(fError);
		
		try {
			Process p=pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
