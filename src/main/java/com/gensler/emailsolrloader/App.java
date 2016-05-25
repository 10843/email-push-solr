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
    public static Long filesFound = 0L;
    public static Long filesInserted = 0L;
    
    public static void main( String[] args ) throws MessagingException, FileNotFoundException
    {
        if (args.length > 0){
            String rootFileName = args[0];
                
            File root = new File(rootFileName);
        
            processFile(root);
        
            System.out.println(" Files inserted " + filesInserted + " Files found " + filesFound);
        }
        
    }
    
    
    private static void processFile(File file){
        if (file!= null && !file.isHidden()){
            if (file.isDirectory()){
                File[] listFiles = file.listFiles();
                System.out.println(file.getName() + " " + listFiles.length);
                filesFound += listFiles.length;
                for(int i = 0; i < listFiles.length; i++){
                    processFile(listFiles[i]);
                }
            }else{
                if (file.getName().endsWith("eml")){
                    MimeEmailParser parser = new MimeEmailParser(file);
                    Email email = parser.getEmail();
                    //solrServer.addEmailToIndex(email);
                    filesInserted++;
            
                } else {
                    if (file.getName().endsWith("msg")){
                        MsgEmailParser parser = new MsgEmailParser(file);
                        Email email = parser.getEmail();
                        //solrServer.addEmailToIndex(email);
                        filesInserted++;
                        
                    }
                }
            }
        }
    }

}
