package org.xi.quick.test.designpattern.facade;

import java.io.UnsupportedEncodingException;

public class ComputerFacade {

    private final long BOOT_ADDRESS = 1000L;
    private final long BOOT_SECTOR = 1000L;
    private final int SECTOR_SIZE = 1000;


    private Cpu processor;
    private Memory ram;
    private HardDrive hd;

    public ComputerFacade() {

        this.processor = new Cpu();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() throws UnsupportedEncodingException {
        processor.freeze();
        ram.load(BOOT_ADDRESS, hd.read(BOOT_SECTOR, SECTOR_SIZE));
        processor.jump(BOOT_ADDRESS);
        processor.execute();
    }
}
