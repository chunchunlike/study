package org.xi.quick.test.reflection;

import java.lang.reflect.Method;
import java.math.BigDecimal;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        System.out.println(RefleactionModel.class.getName());
        Class clazz = Class.forName(RefleactionModel.class.getName());

        //创建此Class 对象所表示的类的一个新实例
        Object obj = clazz.newInstance(); //调用了Employee的无参数构造方法.

        Method[] ms = clazz.getMethods();
        for (Method m : ms) {
                if (m.getParameterTypes().length == 1) {
                    System.out.println(m.getName());
                    Class<?> clazzParameterType = m.getParameterTypes()[0];
                    //m.invoke(obj,)
                    System.out.println(clazzParameterType.getName());
                    //break;
                }
        }


    }
}


class RefleactionModel {
    private String name;
    private int age;
    private BigDecimal deposit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
}