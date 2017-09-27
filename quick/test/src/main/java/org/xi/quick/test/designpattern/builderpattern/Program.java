package org.xi.quick.test.designpattern.builderpattern;

/**
 * 建造者模式测试类
 */
public class Program {

    public static void main(String[] args) {

        HouseBuilder builder = new HouseBuilderImpl();
        HouseBuildDirector director = new HouseBuildDirector();
        System.out.println(director.constructHouse(builder));
    }
}
