package org.xi.quick.test.designpattern.builderpattern;

public interface HouseBuilder {

    /**
     * 建造地基
     */
    void  buildBasic();

    /**
     * 砌墙
     */
    void  buildWalls();

    /**
     * 建造屋顶
     */
    void  buildRoof();

    /**
     * 创建
     * @return 房屋
     */
    House buildHouse();
}
