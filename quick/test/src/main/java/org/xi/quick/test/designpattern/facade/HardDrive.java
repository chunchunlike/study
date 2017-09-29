package org.xi.quick.test.designpattern.facade;

public class HardDrive {

    public byte[] read(long bootSector, int size) {

        System.out.println("HardDrive boot address " + bootSector +", boot sector size " + size);
        return new byte[] {112,113,114,115};
    }
}
