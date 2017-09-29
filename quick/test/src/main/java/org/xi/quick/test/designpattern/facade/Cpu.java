package org.xi.quick.test.designpattern.facade;

public class Cpu {

    public void freeze() {
        System.out.println("CPU freeze");
    }
    public void jump(long position) {
        System.out.println("CPU jump position " + position);
    }
    public void execute() {
        System.out.println("CPU execute");
    }
}
