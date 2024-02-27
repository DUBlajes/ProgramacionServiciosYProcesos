package ejercicio3;


import java.io.*;
import java.net.*;
import java.util.Scanner;

import ejercicio2.Pikachu;

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
             if(respuestaServidor.equals("Acceso permitido")) {
            	 Pikachu pikachu = new Pikachu();

                 // Flujo de salida para enviar el objeto Pikachu al servidor
                 ObjectOutputStream salidaObjeto = new ObjectOutputStream(socket.getOutputStream());
                 salidaObjeto.writeObject(pikachu);
                 salidaObjeto.flush();

                 // Flujo de entrada para recibir datos del servidor
                 DataInputStream entrada2 = new DataInputStream(socket.getInputStream());
                 String mensajeServidor2 = entrada2.readUTF();
                 System.out.println("Servidor: " + mensajeServidor2);

                 // Leer respuesta del usuario desde la consola
                 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                 String respuesta = br.readLine();

                 // Enviar respuesta al servidor
                 DataOutputStream salida2 = new DataOutputStream(socket.getOutputStream());
                 salida2.writeUTF(respuesta);

                 // Si el cliente contesta 'Y', recibir el objeto Pikachu evolucionado del servidor
                 if (respuesta.equalsIgnoreCase("Y")) {
                     ObjectInputStream entradaObjeto = new ObjectInputStream(socket.getInputStream());
                     Pikachu pikachuEvolucionado = (Pikachu) entradaObjeto.readObject();
                     System.out.println("¡Felicidades, tu pikachu "+pikachu.getNombre()+" ha evolucionado a " + pikachuEvolucionado.getNombre()+"!");
                     // Cerrar flujo de entrada de objeto
                     entradaObjeto.close();
                 } else {
                     // Leer el mensaje "Que lástima" del servidor
                     mensajeServidor2 = entrada2.readUTF();
                     System.out.println("Servidor: " + mensajeServidor2);
                 }
             }else {
            	 System.out.println("Las contraseñas no coinciden.");
             }

             // Cerrar flujos y socket
             salida.close();
             entrada.close();
             socket.close();
         } catch (IOException e) {
             System.err.println("Error: " + e.getMessage());
         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
 }
