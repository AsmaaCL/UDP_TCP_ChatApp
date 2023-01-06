
import java.net.*;
import java.io.*;


class UDPServer {
    private DatagramSocket udpSocket;
    private int port;

    public UDPServer(String port) throws SocketException, IOException {
        this.port = port;
        this.udpSocket = new DatagramSocket(this.port);
    }
    private void listen() throws Exception {
        System.out.println("-- Running Server at " + InetAddress.getLocalHost() + "--");
        String msg;

        while (true) {

            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // blocks until a packet is received
            udpSocket.receive(packet);
            msg = new String(packet.getData()).trim();

            System.out.println("Message from " + packet.getAddress().getHostAddress() + ": " + msg);
        }
    }

    public static void main(String[] args) throws Exception {
        UDPServer client = new UDPServer(args[0]);
        client.listen();
    }
}


