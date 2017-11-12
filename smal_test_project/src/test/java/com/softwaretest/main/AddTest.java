package com.softwaretest.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddTest {
    @Test
    public void doAdd() throws Exception {
        int expected = 2;
        int actual = Add.doAdd(3,1);
        assertEquals(expected,actual);
    }

}