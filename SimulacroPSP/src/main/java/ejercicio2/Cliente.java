package ejercicio2;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 61000);

            // Crear objeto Pikachu
            Pikachu pikachu = new Pikachu();

            // Flujo de salida para enviar el objeto Pikachu al servidor
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(socket.getOutputStream());
            salidaObjeto.writeObject(pikachu);
            salidaObjeto.flush();

            // Flujo de entrada para recibir datos del servidor
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String mensajeServidor = entrada.readUTF();
            System.out.println("Servidor: " + mensajeServidor);

            // Leer respuesta del usuario desde la consola
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String respuesta = br.readLine();

            // Enviar respuesta al servidor
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            salida.writeUTF(respuesta);

            // Si el cliente contesta 'Y', recibir el objeto Pikachu evolucionado del servidor
            if (respuesta.equalsIgnoreCase("Y")) {
                ObjectInputStream entradaObjeto = new ObjectInputStream(socket.getInputStream());
                Pikachu pikachuEvolucionado = (Pikachu) entradaObjeto.readObject();
                System.out.println("¡Felicidades, tu pikachu "+pikachu.getNombre()+" ha evolucionado a " + pikachuEvolucionado.getNombre()+"!");
                // Cerrar flujo de entrada de objeto
                entradaObjeto.close();
            } else {
                // Leer el mensaje "Que lástima" del servidor
                mensajeServidor = entrada.readUTF();
                System.out.println("Servidor: " + mensajeServidor);
            }

            // Cerrar flujos y socket
            salida.close();
            entrada.close();
            salidaObjeto.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


