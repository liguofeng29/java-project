package org.lee.java;

// import org.junit.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
// import org.junit.jupiter.api.*;

// import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for simple App.
 */
public class StandardTest {

    @BeforeAll
    static void initAll() throws Exception {
        System.out.println("最初に一回実行");
    }

    @BeforeEach
    void init() {
        System.out.println("テスト前実行");
    }

    @Test
    void succeedingTest() {
        assertTrue(true);
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("無効テスト")
    void skippedTest() {
        // not executed
    }

    @AfterEach
    void tearDown() {
        System.out.println("テスト後実行");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("最後に一回実行");
    }
}