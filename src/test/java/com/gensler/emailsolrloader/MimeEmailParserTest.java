/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gensler.emailsolrloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.mail.MessagingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Eric Theis <eric_theis@gensler.com>
 */
public class MimeEmailParserTest {
    
    public MimeEmailParserTest() {
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
     * Test of getEmail method, of class MimeEmailParser.
     */
    
    @Test
    public void testGetEmailWAttachments() throws FileNotFoundException, MessagingException, IOException {
        System.out.println("getEmailWAttachments");
        //put file into project
        File file = new File("/Users/10843/Documents/EmailHistory/2014-05/attachments.eml");
        
        MimeEmailParser instance = new MimeEmailParser(file);
        
        Email expResult  = new Email();
        
        expResult.setId("<CFA2E18C.3AA6%nicholas_mckinney@gensler.com>");
        expResult.setSender("Nicholas McKinney");
        expResult.setSubject("attachments");
        
        expResult.addRecipient_cc("Flora Pletman");
        expResult.addRecipient_cc("Erik Meister");
        expResult.addRecipient_cc("Eric Theis");

        expResult.addRecipient_to("Surendra Mathe");
        expResult.addRecipient_to("Gautam Gaind");

        
        Email result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

   
    @Test
    public void testGetEmail() throws FileNotFoundException, MessagingException, IOException {
        System.out.println("getEmail");
        
        File file = new File("/Users/10843/Documents/EmailHistory/2014-01/sorry.eml");
        
        MimeEmailParser instance = new MimeEmailParser(file);
        
        Email expResult  = new Email();
        
        expResult.setId("<31502544D2F6804A9D919CDC3A38DB16254F2611@fw86.gensler.ad>");
        expResult.setSender("Janie Beaman");
        expResult.setSubject("sorry");
        
        expResult.addRecipient_to("Eric Theis");

        expResult.setReceive_date(new Date(1427420801596L));
        
        Email result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetEmailWWonkyDate() throws FileNotFoundException, MessagingException, IOException {
        System.out.println("getEmail");
        
        File file = new File("/Users/10843/Documents/EmailHistory/2015-11/REMINDER-  Scarborough Citizen Cabinet Survey.eml");
        
        MimeEmailParser instance = new MimeEmailParser(file);
        
        Email expResult  = new Email();
        
        expResult.setId("<8d27a573-e2a7-49da-9eb5-937ab9139a2b@BN1AFFO11FD012.protection.gbl>");
        expResult.setSender("Scarborough Study");
        expResult.setSubject("REMINDER:  Scarborough Citizen Cabinet Survey boundary=\"--boundary_45865_09121867-f21f-472b-a06d-508d4ec8f42a\"");
        
        expResult.addRecipient_to("ERIC_THEIS@GENSLER.COM");

        expResult.setReceive_date(new Date(1446497603000L));
        
        Email result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
}