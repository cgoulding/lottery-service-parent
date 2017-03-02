package com.goulding.connor.lottery.service.entity;

import java.io.Serializable;

/**
 * Created by connor.
 */
public class LineEntity implements Serializable {

    private String  lineUuid;
    private Integer number1;
    private Integer number2;
    private Integer number3;

    public LineEntity() {
    }

    public LineEntity(String lineUuid, Integer number1, Integer number2, Integer number3) {
        this.lineUuid = lineUuid;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    public String getLineUuid() {
        return lineUuid;
    }

    public Integer getNumber1() {
        return number1;
    }

    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    public Integer getNumber2() {
        return number2;
    }

    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }

    public Integer getNumber3() {
        return number3;
    }

    public void setNumber3(Integer number3) {
        this.number3 = number3;
    }
}
