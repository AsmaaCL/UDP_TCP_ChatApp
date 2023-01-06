import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


class TCP_Server {

    private static ServerSocket socket;
    private static Socket TCPConnexion = null;
    private static DataInputStream in = null;
    private static DataOutputStream out = null;
    private static int port, etat_server;
    private static String data, data_out;

    public static void main(String[] args) throws IOException {
        try {
            port = Integer.parseInt(args[0]);
            etat_server = 0;
            TCP_Server server = new TCP_Server(port, etat_server);
        }
        catch(Exception e) {
            TCP_Server server = new TCP_Server();
        }
        socket.close();
    }

    public TCP_Server() throws IOException {
        this.port = 8888; //default port
        this.etat_server = 1;
        new TCP_Server(port, etat_server);
    }

    public TCP_Server(int port, int etat) throws IOException {
        this.port = port;
        socket = new ServerSocket(port);
        toString(etat);
        System.out.println("Server waiting for a client :");
        TCPConnexion = socket.accept();
        System.out.println("Client connected");
        in = new DataInputStream(TCPConnexion.getInputStream());
        out = new DataOutputStream(TCPConnexion.getOutputStream());


        while(true) {
            try {
                data = in.readUTF();
                System.out.println("\nData receive : " + data);
                data_out = convert_hex(data);
                System.out.println("Data send : " + data_out);
                out.writeUTF(data_out + "\n");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String convert_hex(String data){
        char ch[] = data.toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        String result = sb.toString();
        return result;
    }

    public String toString(int etat) {
        if (etat==0) {
            System.out.println("Server connected");
            System.out.println("Port : " + this.port);
        }
        else if (etat==1) {
            System.out.println("Server connected");
            System.out.println("Unaccepted argument (conformity), Default value :");
            System.out.println("Port : " + this.port);
        }
        else {
            System.out.println("Server disconnected");
        }
        return null;
    }
}