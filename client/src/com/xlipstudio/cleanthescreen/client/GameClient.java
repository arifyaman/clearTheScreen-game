package com.xlipstudio.cleanthescreen.client;

import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.request.Request;
import com.xlipstudio.cleanthescreen.communication.request.RequestType;
import com.xlipstudio.cleanthescreen.communication.sub.WrapType;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient extends Thread{
    private Socket socket = null;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String clientId = "";
    private GameClientCallbacks gameClientCallbacks;



    public GameClient(GameClientCallbacks gameClientCallbacks) {
        this.gameClientCallbacks = gameClientCallbacks;
        try {
            socket = new Socket("localhost", 36813);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.clientId = "a8ys791238hwdmf";
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read() {
        while (true) {
            try {
                Wrap wrap = ((Wrap) inputStream.readObject());
                GameClient.this.processWrap(wrap);
            } catch (IOException e) {

            }catch (Exception e2) {

            }

        }

    }

    @Override
    public void run() {
        read();
    }

    public  void processWrap(Wrap wrap) {
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

    public interface GameClientCallbacks{
        void wrapReceived(Wrap wrap);
    }


}
