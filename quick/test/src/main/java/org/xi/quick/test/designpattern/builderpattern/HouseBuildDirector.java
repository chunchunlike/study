package org.xi.quick.test.designpattern.builderpattern;

public class HouseBuildDirector {

    public House constructHouse(HouseBuilder builder) {

        builder.buildBasic();
        builder.buildWalls();
        builder.buildRoof();

        return builder.buildHouse();
    }
}
