package ejercicio2;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(61000);
            System.out.println("Servidor esperando conexiones...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostName());

            // Flujo de entrada para recibir el objeto Pikachu del cliente
            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());

            // Flujo de salida para enviar datos al cliente
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // Recibir el objeto Pikachu del cliente
            Pikachu pikachu = (Pikachu) entradaObjeto.readObject();
            System.out.println("Tu pikachu se llama " + pikachu.getNombre());

            // Mandar mensaje al cliente
            salida.writeUTF("¿Quieres usar una Piedra Trueno en tu pikachu " + pikachu.getNombre() + "? (Y/N)");

            // Flujo de entrada para recibir respuesta del cliente
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            String respuestaCliente = entrada.readUTF();

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
                salida.writeUTF("Qué lástima. Pues tu "+ pikachu.getNombre()+" se queda como estaba.");
            }

            // Cerrar flujos y socket
            salida.close();
            entrada.close();
            entradaObjeto.close();
            cliente.close();
            servidor.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


