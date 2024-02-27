package ejercicio3;

import java.io.*;
import java.net.*;
import java.security.*;

import ejercicio2.Pikachu;

public class Servidor {
	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(6000);
			System.out.println("CLASE SERVIDOR." + "\nServidor esperando conexiones...");

			Socket cliente = servidor.accept();
			System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostName());

			// Contraseña almacenada en el servidor
			String passAlmacenada = "cenec";
			// Encriptar la contraseña almacenada con SHA-256 y convertirla a hexadecimal
			String passEncriptada = Hexadecimal(encriptarSHA256(passAlmacenada));

			// Flujo de salida para enviar datos al cliente
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

			// Flujo de entrada para recibir datos del cliente
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());

			// Mensaje al cliente solicitando contraseña
			salida.writeUTF("Por favor, ingrese la contraseña:");

			// Recibir la contraseña del cliente
			String passCliente = entrada.readUTF();

			// Encriptar la contraseña recibida del cliente con SHA-256 y convertirla a
			// hexadecimal
			String passClienteEncriptada = Hexadecimal(encriptarSHA256(passCliente));

			// Comparar las contraseñas
			if (passEncriptada.equals(passClienteEncriptada)) {
				salida.writeUTF("Acceso permitido");
				// Flujo de entrada para recibir el objeto Pikachu del cliente
	            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());

	            // Flujo de salida para enviar datos al cliente
	            DataOutputStream salida2 = new DataOutputStream(cliente.getOutputStream());

	            // Recibir el objeto Pikachu del cliente
	            Pikachu pikachu = (Pikachu) entradaObjeto.readObject();
	            System.out.println("Tu pikachu se llama " + pikachu.getNombre());

	            // Mandar mensaje al cliente
	            salida2.writeUTF("¿Quieres usar una Piedra Trueno en tu pikachu " + pikachu.getNombre() + "? (Y/N)");

	            // Flujo de entrada para recibir respuesta del cliente
	            DataInputStream entrada2 = new DataInputStream(cliente.getInputStream());
	            String respuestaCliente = entrada2.readUTF();

	            // Procesar la respuesta del cliente
	            if (respuestaCliente.equalsIgnoreCase("Y")) {
	                // Cambiar el nombre del Pikachu a "Raichu"
	                pikachu.setNombre("Raichu");
	                // Enviar el objeto Pikachu evolucionado y un mensaje al cliente
	                ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
	                salidaObjeto.writeObject(pikachu);
	                salidaObjeto.flush();
	                // Cerrar flujo de salida de objetos
	                salidaObjeto.close();
	            } else {
	                // Enviar un mensaje al cliente
	                salida2.writeUTF("Qué lástima. Pues tu "+ pikachu.getNombre()+" se queda como estaba.");
	            }
			} else {
				salida.writeUTF("Acceso denegado");
			}

			// Cerrar flujos y socket
			salida.close();
			entrada.close();
			cliente.close();
			servidor.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para encriptar una cadena con SHA-256 y convertirla a hexadecimal
	private static byte[] encriptarSHA256(String contraseña) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			return digest.digest(contraseña.getBytes("UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// CONVIERTE UN ARRAY DE BYTES A HEXADECIMAL
	static String Hexadecimal(byte[] resumen) {
		String hex = " ";
		for (int i = 0; i < resumen.length; i++) {
			String h = Integer.toHexString(resumen[i] & 0xFF);
			if (h.length() == 1)
				hex += "0";
			hex += h;
		}
		return hex.toUpperCase();
	}
}
