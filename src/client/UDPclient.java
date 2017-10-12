package client;

import hash.Hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Oleg on 12.10.2017.
 * oleg.kenunen@gmail.com
 */
public class UDPclient {
    public static void main(String args[]) throws Exception {
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[256];
        byte[] receiveData = new byte[256];
        String sentence = inFromUser.readLine();
        System.out.println("local string: " + sentence);
        System.out.println("local MD5: " + Hash.md5(sentence));
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String recievedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + recievedSentence);
        clientSocket.close();
    }
}
