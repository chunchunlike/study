package org.xi.quick.test.designpattern.abstractfactorypattern;

public class WinFactory implements GUIFactory {

    /**
     * 创建Windows绘制
     * @return
     */
    @Override
    public Paint createPaint() {

        return new WinPaint();
    }
}
