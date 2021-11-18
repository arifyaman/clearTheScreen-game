package com.xlipstudio.cleanthescreen.client;

import com.xlipstudio.cleanthescreen.communication.Wrap;

public interface GameClient {
     void processWrap(Wrap wrap);
     void dispatchWrap(Wrap wrap);
     void register();
     void start();
     boolean isConnected();
     void disconnect();

}
