package org.xi.quick.test.designpattern.abstractfactorypattern;

import java.util.Random;

public class Program {

    public static void main(String[] args) {

        GUIFactory factory;

        String appearance = randomAppearance();

        if (appearance.equals("Mac")) {
            factory = new MacFactory();
        } else if(appearance.equals("Windows")) {
            factory = new WinFactory();
        } else {
            System.out.println("没有当前操作系统");
            return;
        }

        Paint paint = factory.createPaint();
        paint.paintButton();
        paint.paintWindow();
    }

    public static String randomAppearance() {

        String[] appearanceArray = new String[3];

        appearanceArray[0] = "Mac";
        appearanceArray[1] = "Windows";
        appearanceArray[2] = "error";

        Random random = new Random();

        int randomNumber = random.nextInt(3);

        return appearanceArray[randomNumber];
    }
}
