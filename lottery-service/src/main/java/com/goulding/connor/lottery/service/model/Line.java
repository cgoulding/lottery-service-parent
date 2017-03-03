package com.goulding.connor.lottery.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Connor Goulding
 */
public class Line {
    private final String lineUuid;
    private final Integer number1;
    private final Integer number2;
    private final Integer number3;

    /**
     * Default constructor for immutable class
     *
     * Json annotations are for the purposes of serialisation
     *
     * @param lineUuid
     * @param number1
     * @param number2
     * @param number3
     */
    @JsonCreator
    public Line(@JsonProperty("lineUuid") String lineUuid, @JsonProperty("number1") Integer number1, @JsonProperty("number2") Integer number2,
                @JsonProperty("number3") Integer number3) {
        this.lineUuid = lineUuid;
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

    public String getLineUuid() {
        return lineUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Line line = (Line) o;

        if (number1 != null ? !number1.equals(line.number1) : line.number1 != null)
            return false;
        if (number2 != null ? !number2.equals(line.number2) : line.number2 != null)
            return false;
        return number3 != null ? number3.equals(line.number3) : line.number3 == null;
    }

    @Override
    public int hashCode() {
        int result = number1 != null ? number1.hashCode() : 0;
        result = 31 * result + (number2 != null ? number2.hashCode() : 0);
        result = 31 * result + (number3 != null ? number3.hashCode() : 0);
        return result;
    }
}
