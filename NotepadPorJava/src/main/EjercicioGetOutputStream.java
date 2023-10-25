package main;
import java.io.*;
import java.util.Scanner;
public class EjercicioGetOutputStream {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Dime la nueva fecha (dd/mm/aaaa)");
		String fecha=sc.nextLine();
		ProcessBuilder pb=new ProcessBuilder("CMD","/C","date"); 
		
		File fOut=new File("SalidaFecha.txt");
		File fError=new File("ErrorFecha.txt");
		
		pb.redirectOutput(fOut);
		pb.redirectError(fError);
		
		try {
			
			Process p=pb.start();
			cambiarfecha(p,fecha);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private static void cambiarfecha(Process p, String fecha) {
		OutputStream os= p.getOutputStream(); 
		OutputStreamWriter ow=new OutputStreamWriter(os);
		BufferedWriter bw=new BufferedWriter(ow);
		try {
			bw.write(fecha);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
