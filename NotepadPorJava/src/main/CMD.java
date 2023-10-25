package main;
import java.io.*;
public class CMD {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb=new ProcessBuilder("CMD","/C","ipconfig");
			Process p=pb.start();
			InputStream is=p.getInputStream();
			int c;
			while((c=is.read())!=-1) {
				System.out.print((char)c);
			}
			is.close();

		

	}

}
