/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gensler.emailsolrloader;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eric Theis <eric_theis@gensler.com>
 */
public class MsgEmailParserTest {
    
    public MsgEmailParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEmail method, of class MsgEmailParser.
     */
    @Test
    public void testGetEmail() throws IOException {
        System.out.println("getEmail");
        
        File file = new File("/Users/10843/Documents/EmailHistory/2012-05/Your account has been created.msg");
        
        MsgEmailParser instance = new MsgEmailParser(file);
        
        Email expResult = new Email();
        expResult.setId("<20120529161051.9755.1954112478.swift@projects.zdca.net>");
        expResult.setSender("ZDCA Project Management");
        expResult.setSubject("Your account has been created");
        
        expResult.addRecipient_to("Eric Theis");
        
        
        Email result = instance.getEmail();
        assertEquals(expResult, result);
    }
    
}
