import java.io.*;
import java.net.*;
import java.util.Scanner;

class UDPClient {
    private DatagramSocket udpSocket;
    private InetAddress serverAddress;
    private int port;
    private Scanner scanner;
    private UDPClient(String destinationAddr, int port) throws IOException {
        this.serverAddress = InetAddress.getByName(destinationAddr);
        this.port = port;
        udpSocket = new DatagramSocket();
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        UDPClient sender = new UDPClient(args[0], Integer.parseInt(args[1]));
        System.out.println("-- Running UDP Client at " + InetAddress.getLocalHost() + " --");
        sender.start();
    }
    private int start() throws IOException {
        String in;
        while (true) {
            in = scanner.nextLine();

            DatagramPacket p = new DatagramPacket(
                    in.getBytes(), in.getBytes().length, serverAddress, port);

            this.udpSocket.send(p);
        }
    }
}