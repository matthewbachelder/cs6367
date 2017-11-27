package com.softwaretest.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class ABSMutationExampleTest {
    @Test
    public void getValue() throws Exception {
        assertEquals(5, ABSMutationExample.getValue(5));
    }

}