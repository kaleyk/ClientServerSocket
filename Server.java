import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket = new DatagramSocket(9876);

            //buffer to receive incoming dat
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            //wait for data
            System.out.println("Server socket created. Waiting for incoming packets..");

            while (true) {

                serverSocket.receive(incoming);
                byte[] data = incoming.getData();
                String packetReceived = new String(data, 0, incoming.getLength());

                System.out.println(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + packetReceived);

                packetReceived = "PONG : " + packetReceived;
                DatagramPacket dp = new DatagramPacket(packetReceived.getBytes(), packetReceived.getBytes().length, incoming.getAddress(), incoming.getPort());
                serverSocket.send(dp);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}




