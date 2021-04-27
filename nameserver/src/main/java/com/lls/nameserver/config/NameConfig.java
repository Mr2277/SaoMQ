package com.lls.nameserver.config;


import com.lls.common.annotation.ConfigResolve;

public class NameConfig {

    @ConfigResolve(value = "server.ip")
    private String serverIP;

    @ConfigResolve(value = "server.port")
    private Integer serverPort;
}
