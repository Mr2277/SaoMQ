package com.lls.nameserver.bean;


import com.lls.nameserver.controller.ProducerController;
import com.lls.remoting.bean.MessageInfo;

public class Producer extends ClientAbstract {
    public static void main(String[] args) throws InterruptedException {
        ProducerController controller = new ProducerController();
        controller.start();
        for (int i = 0; i < 10000000; i++) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setBody("message");
            messageInfo.setCreateTime(String.valueOf(System.currentTimeMillis()));
            controller.send(messageInfo);
        }

    }
}
