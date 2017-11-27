package com.softwaretest.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class CRCRMutationExampleTest {
    @Test
    public void getValue() throws Exception {

        assertEquals(5, CRCRMutationExample.getValue(0));
    }

}