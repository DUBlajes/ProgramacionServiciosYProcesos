package main;
import java.io.*;
public class ProcesosActivos {

	public static void main(String[] args) {
		ProcessBuilder pb=new ProcessBuilder("CMD","/C","tasklist");
		try {
			Process p=pb.start();
			InputStream is=p.getInputStream();
			int c;
			while ((c=is.read())!=-1) {
				System.out.print((char)c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
