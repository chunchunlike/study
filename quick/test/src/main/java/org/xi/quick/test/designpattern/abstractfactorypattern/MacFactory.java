package org.xi.quick.test.designpattern.abstractfactorypattern;

public class MacFactory implements GUIFactory {

    /**
     * 创建Mac绘制
     * @return
     */
    @Override
    public Paint createPaint() {

        return new MacPaint();
    }
}
