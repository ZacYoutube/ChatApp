
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;
    ArrayList<String> list = new ArrayList<String>();

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server has started...");

            System.out.println("Awaiting a client to connect...");

            socket = server.accept();
            InetAddress ipAddress = server.getInetAddress();
            System.out.println("Server accepted and connected to " + ipAddress);

            input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String connectionType = "";
            String typeInput = "";

            while (!connectionType.equals("EXIT")) {

                try {
                    connectionType = input.readUTF();
                    System.out.println(ipAddress + " sends " + connectionType);
                    if (connectionType.equals("UPPERCASE"))
                        System.out.println("200 OK");

                    while (connectionType.equals("UPPERCASE")) {
                        typeInput = input.readUTF();

                        if (typeInput.length() == 1 && typeInput.contains(".")) {
                            System.out.println(".");
                            connectionType = typeInput;

                            for (int i = 0; i < list.size(); i++) {
                                System.out.println(list.get(i));
                            }
                            list.clear();
                        }

                        if ((typeInput.length() > 1 && typeInput.contains(".")) || (typeInput.length() == 1 && !typeInput.contains(".")) || (typeInput.length() > 1 && !typeInput.contains("."))) {
                            System.out.println(typeInput);
                        }

                        typeInput = typeInput.toUpperCase();
                        list.add(typeInput);
                    }

                    if (connectionType.equals("LOWERCASE"))
                        System.out.println("200 OK");
                    while (connectionType.equals("LOWERCASE")) {
                        typeInput = input.readUTF();
                        if (typeInput.length() == 1 && typeInput.contains(".")) {
                            connectionType = typeInput;
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println(list.get(i));
                            }
                            list.clear();
                        }

                        if ((typeInput.length() > 1 && typeInput.contains(".")) || (typeInput.length() == 1 && !typeInput.contains(".")) || (typeInput.length() > 1 && !typeInput.contains("."))) {
                            System.out.println(typeInput);
                        }

                        typeInput = typeInput.toLowerCase();
                        list.add(typeInput);

                    }
                    if (connectionType.equals("REVERSE"))
                        System.out.println("200 OK");
                    while (connectionType.equals("REVERSE")) {
                        typeInput = input   .readUTF();
                        if (typeInput.length() == 1 && typeInput.contains(".")) {
                            connectionType = typeInput;
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println(list.get(i));
                            }
                            list.clear();
                        }

                        String x = typeInput;

                        if ((typeInput.length() > 1 && typeInput.contains(".")) || (typeInput.length() == 1 && !typeInput.contains(".")) || (typeInput.length() > 1 && !typeInput.contains(".")))
                            System.out.println(typeInput);

                        char[] chars = new char[x.length()];
                        for (int i = 0; i < x.length(); i++) {
                            chars[x.length() - i - 1] = x.charAt(i);
                        }
                        String newStr = new String(chars);
                        list.add(newStr);
                    }
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("200 OK");
            socket.close();
            input.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(5000);
    }


} 