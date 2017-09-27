package org.xi.quick.test.designpattern.builderpattern;

public class House {

    /**
     * 地基
     */
    private String basic;

    /**
     * 墙
     */
    private String wall;

    /**
     * 屋顶
     */
    private String roof;

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    @Override
    public String toString() {
        return "House [basic = " + basic + ", wall = " + wall + ", roof = " + roof + "]";
    }
}
