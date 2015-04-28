package com.gensler.emailsolrloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static SolrEmailInsert solrServer = new SolrEmailInsert();
    
    public static void main( String[] args ) throws MessagingException, FileNotFoundException
    {

    //    SolrEmailInsert solrServer = new SolrEmailInsert();
//        File file = new File("/Users/10843/EmailHistory/2014-05/attachments.eml");
//        try {
//            MimeEmailParser parser = new MimeEmailParser(file);
//            Email email = parser.getEmail();
//            solrServer.addEmailToIndex(email);
//            
//        } catch (IOException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        File root = new File("/Users/10843/EmailHistory/");
        
        processFile(root);
        
        
        
        
    }
    
    
    private static void processFile(File file){
        if (file!= null && !file.isHidden()){
            if (file.isDirectory()){
                File[] listFiles = file.listFiles();
                System.out.println(file.getName() + " " + listFiles.length);
                for(int i = 0; i < listFiles.length; i++){
                    processFile(listFiles[i]);
                }
            }else{
                try {
                    MimeEmailParser parser = new MimeEmailParser(file);
                    Email email = parser.getEmail();
                    solrServer.addEmailToIndex(email);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        }
    }
}
