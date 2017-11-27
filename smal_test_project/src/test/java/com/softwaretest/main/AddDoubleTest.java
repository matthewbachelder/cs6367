package com.softwaretest.main;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddDoubleTest {
    @Test
    public void doAdd() throws Exception {
        double expected = 4;
        double actual = AddDouble.doAdd(2.0,2.0);
        assertEquals(expected,actual,0);
    }

}