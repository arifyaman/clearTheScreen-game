package com.xlipstudio.cleanthescreen.client;

import com.xlipstudio.cleanthescreen.communication.Wrap;

public interface GameClientCallbacks {
    void wrapReceived(Wrap wrap);
    void unableToConnect();
}
