package com.github.springredis.model;

import java.io.Serializable;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
public class Obj implements Serializable {
    private Integer attr1;
    private String attr2;
    private Long attr3;

    public Integer getAttr1() {
        return attr1;
    }

    public void setAttr1(Integer attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public Long getAttr3() {
        return attr3;
    }

    public void setAttr3(Long attr3) {
        this.attr3 = attr3;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "attr1=" + attr1 +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                '}';
    }
}
