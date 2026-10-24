package com.example.mongodemo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
class SmokeTest {
    @Test void javaToolchainSupports21() { assertEquals(21, Runtime.version().feature()); }
}
