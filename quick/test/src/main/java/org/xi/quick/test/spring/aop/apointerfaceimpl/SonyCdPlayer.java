package org.xi.quick.test.spring.aop.apointerfaceimpl;

import org.springframework.stereotype.Component;
import org.xi.quick.test.spring.aop.aopinterface.CdPlayer;

@Component("cdPlayer")
public class SonyCdPlayer implements CdPlayer {

    @Override
    public int read(String cdName) {
        System.out.println("reading cd");
        return 1;
    }

    @Override
    public void play(String songName) {
        System.out.println("sony playing " + songName);
    }
}
