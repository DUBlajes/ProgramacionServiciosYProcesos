package tcp.psp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente {
	public static void main(String[]args) throws IOException {
		String host="localhost";
		InetAddress hostnum= InetAddress.getLocalHost();
		int puerto = 60897;
		
		Socket cliente = new Socket(hostnum,puerto);
		System.out.println(cliente.getLocalPort());
		System.out.println(cliente.getPort());
		System.out.println(cliente.getInetAddress());
		System.out.println(cliente.getLocalAddress());
		cliente.close();
	}
}
