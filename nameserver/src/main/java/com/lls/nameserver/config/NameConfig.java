package com.lls.nameserver.config;


import com.lls.common.annotation.ConfigResolve;

public class NameConfig {

    @ConfigResolve(value = "nameServer.IP")
    private String nameServerIP;
}
