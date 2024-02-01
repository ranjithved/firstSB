package com.example.firstSB.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;


public class Message {



    private int msgid;
    private String msg;

    public int getMsgid() { return msgid; }

    public void setMsgid(int msgid) {
        this.msgid=msgid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
