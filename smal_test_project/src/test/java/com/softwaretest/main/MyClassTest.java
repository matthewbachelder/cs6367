package com.softwaretest.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyClassTest {
    @Test
    public void myMethod() throws Exception {
        MyClass sut = new MyClass();
        assertTrue(sut.myMethod(1, true));
        assertTrue(sut.myMethod(2, true));
        assertTrue(sut.myMethod(1, false));
        assertTrue(sut.myMethod(2, false));
        assertFalse(sut.myMethod(0, false));
    }

}