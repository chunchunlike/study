package org.xi.quick.test.lambda.stream;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String name;
    private String sex;
    private Double weight;

    public User() {
    }

    public User(Integer id, String name, String sex, Double weight) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[id:" + id + ", name:" + name + ", sex:" + sex + ", weight: " + weight + "]";
    }
}
