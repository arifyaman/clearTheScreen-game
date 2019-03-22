package com.xlipstudio.cleanthescreen.client;

import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.request.Request;
import com.xlipstudio.cleanthescreen.communication.request.RequestType;
import com.xlipstudio.cleanthescreen.communication.sub.WrapType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread implements GameClient {
    private Socket socket = null;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String clientId = "";
    private GameClientCallbacks gameClientCallbacks;


    public Client(GameClientCallbacks gameClientCallbacks, String clientId, String host) throws IOException {
        this.gameClientCallbacks = gameClientCallbacks;

            socket = new Socket(host, 36813);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.clientId = clientId;



    }

    private void read() {
        while (true) {
            try {
                Wrap wrap = ((Wrap) inputStream.readObject());
                Client.this.processWrap(wrap);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                    break;
                } catch (IOException x) {
                    x.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();

            }

        }

    }

    @Override
    public void run() {
        read();
    }

    public void processWrap(Wrap wrap) {
        gameClientCallbacks.wrapReceived(wrap);
    }

    public void register() {
        Wrap wrap = new Wrap();
        wrap.setWrapType(WrapType.REQUEST);

        Request request = new Request();
        request.setRequestType(RequestType.REGISTER);
        request.setPayload(clientId);
        wrap.setRequest(request);
        dispatchWrap(wrap);
    }


    public void dispatchWrap(Wrap wrap) {
        try {
            outputStream.writeObject(wrap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
