import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

import static java.lang.Thread.sleep;

public class Client {
public static int count=0;
    public static void main(String args[]) throws Exception {


        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        try {

            int count = 0;

            while (count <=10) {

                System.out.println("Enter message to send");
                String sentence = "PING";//inFromUser.readLine();
                sendData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                long start = System.nanoTime();
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());
                long end = System.nanoTime();
                long elapsedTime = end - start;
                System.out.print( " IN: " + elapsedTime + " NanoSeconds ");
                System.out.println("FROM SERVER:" + modifiedSentence );
                count++;
            }
            clientSocket.close();
        }
        catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
