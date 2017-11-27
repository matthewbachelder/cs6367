package com.softwaretest.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnaryOperatorMutationExampleTest {
    @Test
    public void doIncrement() throws Exception {
        int x = UnaryOperatorMutationExample.doIncrement(5);
        assertEquals(6,x);
    }

    @Test
    public void doDecrement() throws Exception {
        int x = UnaryOperatorMutationExample.doDecrement(5);
        assertEquals(4,x);
    }

    @Test
    public void returnValue() throws Exception {
        int x = UnaryOperatorMutationExample.returnValue(5);
        assertEquals(5,x);
    }

}