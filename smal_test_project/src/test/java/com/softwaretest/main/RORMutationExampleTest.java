package com.softwaretest.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class RORMutationExampleTest {
    @Test
    public void getValue() throws Exception {

        assertEquals(1,RORMutationExample.getValue());
    }

}