package com.softwaretest.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class AORMutationExampleTest {
    @Test
    public void getValue() throws Exception {
        assertEquals(10, AORMutationExample.getValue());
    }

}