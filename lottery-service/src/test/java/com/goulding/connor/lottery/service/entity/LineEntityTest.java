package com.goulding.connor.lottery.service.entity;

import org.junit.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Connor Goulding
 */
public class LineEntityTest {

    @Test
    public void testStructure() {
        assertThat(LineEntity.class, allOf(
                hasValidBeanConstructor(),
                hasValidGettersAndSetters()
        ));
    }
}
