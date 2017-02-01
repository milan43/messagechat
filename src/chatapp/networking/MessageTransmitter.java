/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meelon
 */
public class MessageTransmitter extends Thread {

    String message, address;
    int port;

    public MessageTransmitter() {
    }

    public MessageTransmitter(String message, String address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {

        try {
            Socket s = new Socket(address, port);

            s.getOutputStream().write(message.getBytes());
            s.close();

        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
