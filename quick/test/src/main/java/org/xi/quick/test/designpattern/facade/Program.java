package org.xi.quick.test.designpattern.facade;

import java.io.UnsupportedEncodingException;

public class Program {

    public static void main(String[] args) throws UnsupportedEncodingException {

        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
