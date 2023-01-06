import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class TCP_Client {

    private static int port;
    private static String address;
    private static Socket socket;
    private static DataOutputStream out_server = null;
    private static DataInputStream in_server = null;
    String rep;

    public static void main(String[] args) throws IOException {
        try {
            address = args[0];
            port = Integer.parseInt(args[1]);
        }
        catch(Exception e) {
            TCP_Client.error_args();
            TCP_Client client = new TCP_Client();
        }
    }

    public TCP_Client() throws IOException {
        this.port = 8888; //default port
        this.address = "localhost"; //default address
        new TCP_Client(this.address, this.port);
    }

    public TCP_Client(String address, int port) throws IOException {

        String data = null;
        boolean run = true;
        try {
            socket = new Socket(address, port);
            out_server = new DataOutputStream(socket.getOutputStream());
            in_server = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            while(run == true) {
                System.out.println("\nEnter String :");
                data = scanner.nextLine();
                if (data.equals("exit")) {
                    run = false;
                    continue;
                }
                out_server.writeUTF(data);
                rep = in_server.readUTF();
                System.out.println("Conversion into hexadecimal : " + rep);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        socket.close();
    }

    public static void error_args(){
        System.out.println("\n-----------------------------------------------");
        System.out.println("Unaccepted arguments (conformity), default values :");
        System.out.println("Address : localhost");
        System.out.println("Port : 8888");
        System.out.println("-----------------------------------------------\n");
    }
}