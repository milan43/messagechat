/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meelon
 */
public class MessageListener extends Thread {

    ServerSocket server;
    int port = 8877;
    WriteMsg write;

   

    public MessageListener(int port, WriteMsg write){
        this.write = write;
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public MessageListener() {
        try {
            server = new ServerSocket();
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        Socket client;

        try {
            while ((client = server.accept()) != null) {
                InputStream inputStream = client.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                String line = br.readLine();
                 if (line != null) {
                    write.message(line);
                }
                client.close();

            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
