package org.xi.quick.test.designpattern.builderpattern;

public class HouseBuilderImpl implements HouseBuilder {


    private House house;

    public HouseBuilderImpl() {
        house = new House();
    }

    /**
     * 建造地基
     */
    @Override
    public void buildBasic() {

        house.setBasic("地基");
    }

    /**
     * 砌墙
     */
    @Override
    public void buildWalls() {

        house.setWall("墙");
    }

    /**
     * 建造屋顶
     */
    @Override
    public void buildRoof() {

        house.setRoof("屋顶");
    }

    /**
     * 创建
     *
     * @return 房屋
     */
    @Override
    public House buildHouse() {
        return house;
    }
}
