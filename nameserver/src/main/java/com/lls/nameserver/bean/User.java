package com.lls.nameserver.bean;

import com.lls.common.annotation.ConfigResolve;
import com.lls.common.annotation.LoadProperty;

@LoadProperty(value = "nameserver.properties")
public class User {

    @ConfigResolve("user.id")
    private String id;

    @ConfigResolve("user.name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
