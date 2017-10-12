package server;

import hash.Hash;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Oleg on 12.10.2017.
 * oleg.kenunen@gmail.com
 */
public class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[256];
        byte[] sendData = new byte[256];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData(),0, receivePacket.getLength());
            System.out.println("RECEIVED: " + sentence);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            //String capitalizedSentence = sentence.toUpperCase();
            String hashOfReceived = Hash.md5(sentence);
            System.out.println("MD5: " + hashOfReceived);
            //sendData = capitalizedSentence.getBytes();
            sendData = hashOfReceived.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
