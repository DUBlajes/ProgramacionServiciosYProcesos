package tcp.psp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[]args) throws IOException {
		int puerto = 60897;
		ServerSocket servidor = new ServerSocket(puerto);
		System.out.println("Escuchando el puerto " + servidor.getLocalPort());
		Socket cliente1 = servidor.accept();
		Socket cliente2 = servidor.accept();
		servidor.close();
	}
}
