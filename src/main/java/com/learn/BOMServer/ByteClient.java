package com.learn.BOMServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ByteClient {

    private final String host;
    private final int port;
    private Socket socket;
    private OutputStream out;
    private InputStream in;

    public ByteClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket(host, port);
        this.out = socket.getOutputStream();
        this.in = socket.getInputStream();
    }

    public void sendMessage(byte[] message) throws IOException {
        System.out.println("Sending: " + Arrays.toString(message));
        out.write(message);
        out.flush();

        // Read response (blocking)
        byte[] responseBuffer = new byte[1024];
        int readLen = in.read(responseBuffer);  // read bytes sent back from server
        if (readLen > 0) {
            byte[] actualResponse = new byte[readLen];
            System.arraycopy(responseBuffer, 0, actualResponse, 0, readLen);
            System.out.println("Received from server: " + Arrays.toString(actualResponse));
        } else {
            System.out.println("No response received from server.");
        }
    }

    public void close() throws IOException {
        socket.close();
    }

    public static void main(String[] args) {
        try {
            ByteClient client = new ByteClient("localhost", 9123);

            client.sendMessage(new byte[]{1, 10, 20});     // Login
            client.sendMessage(new byte[]{2, 30, 40});     // Password
            client.sendMessage(new byte[]{16});             // Connection
            client.sendMessage(new byte[]{(byte) 197, 1, 2, 3, 4, 5}); // OutPaqCDA
            client.sendMessage(new byte[]{27, 9, 8, 7, 6}); // Response

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
