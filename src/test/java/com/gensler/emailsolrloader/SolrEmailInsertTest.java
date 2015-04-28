/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gensler.emailsolrloader;

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
public class SolrEmailInsertTest {
    
    public SolrEmailInsertTest() {
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
     * Test of addEmailToIndex method, of class SolrEmailInsert.
     */
    @Test
    public void testAddEmailToIndex() {
        System.out.println("addEmailToIndex");
        Email email = new Email();
        
        email.setId("test_email");
        email.setSender("noreply@gensler.com");
        email.setSubject("THis is a test");
        email.setMessage("Test. Test. Test.");

        email.addRecipient_cc("first_copy@gensler.com");
        email.addRecipient_cc("second_copy@gensler.com");

        email.addRecipient_to("first_to@gensler.com");
        email.addRecipient_to("second_to@gensler.com");
        
        SolrEmailInsert instance = new SolrEmailInsert();
        boolean expResult = true;
        boolean result = instance.addEmailToIndex(email);
        assertEquals(expResult, result);
    }
}