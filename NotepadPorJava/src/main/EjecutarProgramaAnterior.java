package main;
import java.io.*;
public class EjecutarProgramaAnterior {

	public static void main(String[] args) {
		File ruta=new File(".//bin");
		ProcessBuilder pb=new ProcessBuilder("java","main.Notepad");
		pb.directory(ruta);
		
		try {
			Process p=pb.start();
			InputStream is=p.getInputStream();
			int c;
			while((c=is.read())!=-1) {
				System.out.print((char)c);
			}
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
