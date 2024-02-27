package ejercicio1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
        	Scanner sc=new Scanner(System.in);
            Socket socket = new Socket("localhost", 6000);
            System.out.println("CLASE CLIENTE.");

            // Flujo de entrada para recibir datos del servidor
            DataInputStream entrada = new DataInputStream(socket.getInputStream());

            // Flujo de salida para enviar datos al servidor
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

            // Recibir mensaje del servidor solicitando contraseña
            String mensajeServidor = entrada.readUTF();
            System.out.println("Servidor: " + mensajeServidor);

            // Contraseña a enviar al servidor
            String pass = sc.nextLine();

            // Enviar contraseña al servidor
            salida.writeUTF(pass);

            // Recibir respuesta del servidor
            String respuestaServidor = entrada.readUTF();
            System.out.println("Servidor: " + respuestaServidor);

            // Cerrar flujos y socket
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
