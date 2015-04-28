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
        
        File file = new File("/Users/10843/EmailHistory/2014-05/attachments.eml");
        
        MimeEmailParser instance = new MimeEmailParser(file);
        
        Email expResult  = new Email();
        
        expResult.setId("<CFA2E18C.3AA6%nicholas_mckinney@gensler.com>");
        expResult.setSender("Nicholas McKinney");
        expResult.setSubject("attachments");
        
                StringBuilder sbMessage = new StringBuilder();
                sbMessage.append("\r\n");
                sbMessage.append("\r\n");
                sbMessage.append("\r\n");
                sbMessage.append("\r\n");
                expResult.setMessage(sbMessage.toString());

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
        
        File file = new File("/Users/10843/EmailHistory/2014-01/sorry.eml");
        
        MimeEmailParser instance = new MimeEmailParser(file);
        
        Email expResult  = new Email();
        
        expResult.setId("<31502544D2F6804A9D919CDC3A38DB16254F2611@fw86.gensler.ad>");
        expResult.setSender("Janie Beaman");
        expResult.setSubject("sorry");
        
        StringBuilder sbMessage = new StringBuilder();
        sbMessage.append("Would you please call mobile 213.407.2743\n");
        sbMessage.append(" \n");
        sbMessage.append("                   \n");
        sbMessage.append("Janie Beaman \n");
        sbMessage.append("Firmwide Marketing Manager\n");
        sbMessage.append("+1 213.327.2922 Direct\n");
        sbMessage.append("+1 213.407.2743 Mobile\n");
        sbMessage.append("                   \n");
        sbMessage.append("Gensler \n");
        sbMessage.append("500 S. Figueroa Street\n");
        sbMessage.append("Los Angeles, California 90071\n");
        sbMessage.append("USA \n");
        sbMessage.append("\n");
        sbMessage.append("Gensler.com <http://www.gensler.com>  | Blog <http://www.gensleron.com>  |\n");
        sbMessage.append("Facebook <http://www.facebook.com/GenslerDesign>  | Twitter\n");
        sbMessage.append("<http://www.twitter.com/GenslerOnCities>  | YouTube\n");
        sbMessage.append("<http://www.youtube.com/GenslerTV>\n");
        sbMessage.append(" \n");
        sbMessage.append("What¹s ahead for design and our clients?\n");
        sbMessage.append("Find out here: Gensler  Design Forecast 2014\n");
        sbMessage.append("<http://m.gensler.com/feature/gensler-design-forecast-2014>\n");
        sbMessage.append(" \n"); 
        sbMessage.append("\n");
        sbMessage.append("\n");
        expResult.setMessage(sbMessage.toString());

        expResult.addRecipient_cc("Flora Pletman");
        expResult.addRecipient_cc("Erik Meister");
        expResult.addRecipient_cc("Eric Theis");

        expResult.addRecipient_to("Surendra Mathe");
        expResult.addRecipient_to("Gautam Gaind");
        expResult.setReceive_date(new Date(1427420801596L));
        
        Email result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}