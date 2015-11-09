package com.dandeeee.salt.netchat;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    String name;

    User(String name_){
        name = name_;
    }

    @Override
    public String toString() {
        return name;
    }


}