/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Connor Goulding
 */
public class LineResult {
    private final Line line;
    private final Integer result;

    @JsonCreator
    public LineResult(@JsonProperty("line") Line line, @JsonProperty("result") Integer result) {
        this.line = line;
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public Line getLine() {
        return line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        LineResult that = (LineResult) o;

        if (line != null ? !line.equals(that.line) : that.line != null)
            return false;
        return result != null ? result.equals(that.result) : that.result == null;

    }

    @Override
    public int hashCode() {
        int result1 = line != null ? line.hashCode() : 0;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
