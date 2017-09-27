package org.xi.quick.test.designpattern.abstractfactorypattern;

public class WinPaint implements Paint {

    /**
     * 画出Windows按钮
     */
    @Override
    public void paintButton() {

        System.out.println("Win Button");
    }

    /**
     * 画出窗口
     */
    @Override
    public void paintWindow() {

        System.out.println("Win Window");
    }

}
