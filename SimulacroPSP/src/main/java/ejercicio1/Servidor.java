package ejercicio1;

import java.io.*;
import java.net.*;
import java.security.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(6000);
            System.out.println("CLASE SERVIDOR."
            		+ "\nServidor esperando conexiones...");

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

            // Encriptar la contraseña recibida del cliente con SHA-256 y convertirla a hexadecimal
            String passClienteEncriptada = Hexadecimal(encriptarSHA256(passCliente));

            // Comparar las contraseñas
            if (passEncriptada.equals(passClienteEncriptada)) {
                salida.writeUTF("Acceso permitido");
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

