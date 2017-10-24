package org.lee.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void mainTest() {
        System.out.println("App.mainをテストします。");
        App.main(new String[]{});
    }
}