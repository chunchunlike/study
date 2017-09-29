package org.xi.quick.test.designpattern.facade;

import java.io.UnsupportedEncodingException;

public class Memory {

    public void load(long position, byte[] data) throws UnsupportedEncodingException {

        System.out.println("Memory load position " + position +", data " + new String(data, "utf8"));
    }
}
