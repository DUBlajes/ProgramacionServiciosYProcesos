package main;

import java.io.*;
import java.net.*;

public class EjemploClienteCambioDivisas {

	public static void main(String[] args) throws IOException {
		String host = "localhost";
		int puerto = 6000;// puerto remoto
		System.out.println("CLASE CLIENTE."
				+ "\nPROGRAMA CLIENTE INICIADO.....");
		
		//En el programa cliente es necesario crear un objeto Socket, donde aparece //el nombre del equipo y el puerto donde nos queremos conectar.
		Socket cliente = new Socket(host, puerto);
		
		// CREO FLUJO DE SALIDA AL SERVIDOR
		OutputStream salida = cliente.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream(salida);
		
		// ENVIO UN SALUDO AL SERVIDOR
		flujoSalida.writeUTF("¿Cuánto son 1000 euros en dólares, libras y rublos?");
		
		// CREO FLUJO DE ENTRADA AL SERVIDOR
		DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
		
		// EL SERVIDOR ENVÍA UN MENSAJE
		System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());
		
		// CERRAR STREAMS Y SOCKETS flujoEntrada.close();
		flujoSalida.close();
		cliente.close();
	}
}