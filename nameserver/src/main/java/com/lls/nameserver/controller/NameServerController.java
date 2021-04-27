package com.lls.nameserver.controller;

import com.lls.nameserver.bean.Consumer;
import com.lls.nameserver.bean.Producer;
import com.lls.remoting.RemotingServer;
import com.lls.remoting.RemotingService;

import java.util.List;
import java.util.Map;

public class NameServerController {

    private Map<String, List<Producer>> producerMap;

    private Map<String, List<Consumer>> consumerMap;

    private RemotingService remotingService = new RemotingServer();

    public void start() {
        remotingService.init();
        remotingService.start();
    }

}
