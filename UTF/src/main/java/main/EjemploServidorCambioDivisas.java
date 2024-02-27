package main;

import java.io.*;
import java.net.*;

public class EjemploServidorCambioDivisas {

	public static void main(String[] args) throws IOException {
		// APERTURA DEL SOCKET

		int numeropuerto = 6000;
		ServerSocket servidor = new ServerSocket(numeropuerto);

		// En el programa servidor se crea un objeto ServerSocket invocando al método
		// ServerSocket()
		// en el que indicamos el número de puerto por el que el servidor escucha las
		// peticiones de conexión de clientes.| ServerSocket servidor = new ServerSocket
		// (numeroPuerto);

		System.out.println("CLASE SERVIDOR." + "\nEsperando al cliente....");
		// Necesitamos tambien un objeto Socket para aceptar las peticiones clientes.

		Socket clienteConectado = servidor.accept();

		// CREO FLUJO DE ENTRADA DEL CLIENTE
		InputStream entrada = clienteConectado.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream(entrada);

		// EL CLIENTE ENVIA UN MENSAJE
		System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());

		// CREO FLUJO DE SALIDA AL CLIENTE
		OutputStream salida = clienteConectado.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream(salida);
		double eurosADolares=1000*1.08f;
		double eurosALibras=1000*0.86f;
		double eurosARublos=1000*97.52f;

		// ENVIO UN SALUDO AL CLIENTE
		flujoSalida.writeUTF("1000 euros son "
		+"\n\t"+eurosADolares+" dólares"
		+"\n\t"+eurosALibras+" libras"
		+"\n\t"+eurosARublos+" rublos");

		// CERRAR STREAMS Y SOCKETS
		entrada.close();
		flujoEntrada.close();
		salida.close();
		flujoSalida.close();
		clienteConectado.close();
		servidor.close();
	}
}