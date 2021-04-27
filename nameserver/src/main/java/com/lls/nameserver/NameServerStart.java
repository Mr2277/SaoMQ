package com.lls.nameserver;


import com.lls.nameserver.controller.NameServerController;

public class NameServerStart {

    public static void main(String[] args) {
        NameServerController controller = new NameServerController();
        controller.start();
    }
}
