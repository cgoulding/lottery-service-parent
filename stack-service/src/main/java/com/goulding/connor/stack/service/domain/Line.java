package com.goulding.connor.stack.service.domain;

/**
 * Created by connor on 14/02/17.
 */
public class Line {

    private Long lineId;
    private Integer number1;
    private Integer number2;
    private Integer number3;

    public Line() {
    }

    public Line(Long lineId, Integer number1, Integer number2, Integer number3) {
        this.lineId = lineId;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    public Long getLineId() {
        return lineId;
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
