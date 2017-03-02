package com.goulding.connor.lottery.service.model;

/**
 * Created by connor.
 */
public class Line
{

    private final Integer number1;
    private final Integer number2;
    private final Integer number3;

    public Line(Integer number1, Integer number2, Integer number3) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    public Integer getNumber1() {
        return number1;
    }

    public Integer getNumber2() {
        return number2;
    }

    public Integer getNumber3() {
        return number3;
    }
}
