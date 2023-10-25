package main;
import java.io.*;
public class MuestraErrores {

	public static void main(String[] args) {
		ProcessBuilder pb=new ProcessBuilder("CMD","/C","ipconfigurar");
		try {
			Process p=pb.start();
			InputStream is=p.getErrorStream();
			int cError;
			while ((cError=is.read())!=-1) {
				System.out.print((char)cError);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

