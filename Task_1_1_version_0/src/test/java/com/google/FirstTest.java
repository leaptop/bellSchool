
package com.google;//we need a package because of incapsulation(need to call different methods with the same signature?)

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//researching how the base of junit works
public class FirstTest {

    @BeforeAll
    public static void beforeAll(){
        System.out.println( "beforAll");
    }
    @BeforeEach
    public  void beforeEach(){
        System.out.println( "beforeEach");
    }

    @Test//the sequance of tests is randomized. There are ways to order tests, but it's considered as bad practice
    public void firstTest(){
        System.out.println( "firstTest");
    }
    @Test
    public void secondTest(){
        System.out.println( "secondTest");
    }
    @AfterAll
    public static void afterAll(){
        System.out.println( "afterAll");
    }
}
