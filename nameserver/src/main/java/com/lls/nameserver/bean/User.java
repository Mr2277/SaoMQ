package com.lls.nameserver.bean;

import com.lls.common.annotation.ConfigResolve;
import com.lls.common.annotation.LoadProperty;
import com.lls.nameserver.utils.AnnotationUtil;

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

    public static void main(String[] args) {
        User user = new User();
        AnnotationUtil.loadProperty(user);
        System.out.println(user.id);
        System.out.println(user.name);
    }
}
