package com.lls.remoting.bean;

import java.io.Serializable;

public class MessageInfo implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    private String body;

    private String createTime;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
