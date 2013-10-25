package com.temboo.sdkgen.test;

import org.junit.Test;
import static org.junit.Assert.*;
import com.temboo.sdkgen.SDKGenerator;

public class GenTest {
    @Test
    public void testGen() throws Exception {
        SDKGenerator s = new SDKGenerator();
        s.generateCode("template path", GenTest.class.getResourceAsStream("/tmb-monty-perftest.json"));
    }
}
