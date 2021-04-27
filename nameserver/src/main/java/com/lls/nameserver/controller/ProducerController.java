package com.lls.nameserver.controller;

import com.lls.remoting.RemotingClient;
import com.lls.remoting.bean.MessageInfo;

public class ProducerController {

    private RemotingClient client = new RemotingClient();

    public void start() {
        client.init();
        client.start();
    }

    public void send(MessageInfo messageInfo) {
        client.sendMessage(messageInfo);
    }
}
