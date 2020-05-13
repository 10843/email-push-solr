/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gensler.emailsolrloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Eric Theis <eric_theis@gensler.com>
 */
public final class MimeEmailParser {
    private Email email;
    
    public MimeEmailParser(File file){
        InputStream is = null;
        try {
            email = new Email();
            email.setFilePath(file.getAbsolutePath());
            is = new FileInputStream(file);
            MimeMessage message = new MimeMessage(Session.getDefaultInstance(System.getProperties()),is);
            
            String id = message.getMessageID();
            email.setId(id);
            
            Enumeration<?> headers =  message.getAllHeaders();
            Map<String, String> headerMap = convertHeadersToMap(headers);
            
            String from = extractFieldFromHeaderMap(headerMap,"From");
            email.setSender(extractNameFromAddress(from));
            
            String subject = message.getSubject();
            email.setSubject(subject);
            
            if (message.getContentType().contains("text/plain")){
                email.setMessage(message.getContent().toString());
            }else{
                if(message.getContentType().contains("multipart")){
                    javax.mail.internet.MimeMultipart body = (javax.mail.internet.MimeMultipart) message.getContent();;
                    for (int i = 0; i < body.getCount(); i++){
                        if (body.getBodyPart(i).getContentType().contains("text/plain")){
                            email.setMessage(body.getBodyPart(i).getContent().toString());
                        }
                    }
                }else{
                    System.out.println(file.getName());
                    System.out.println(message.getContentType());
                    
                }
            }
            
            try {
                Address[] to = message.getRecipients(Message.RecipientType.TO);
                email.setRecipient_to(convertAddressArrayToList(to));
            } catch (AddressException ex){
                Logger.getLogger(MimeEmailParser.class.getName()).log(Level.WARNING, file.getAbsolutePath(), ex);
            }

            try {
                Address[] cc = message.getRecipients(Message.RecipientType.CC);
                email.setRecipient_cc(convertAddressArrayToList(cc));
            } catch (AddressException ex){
                Logger.getLogger(MimeEmailParser.class.getName()).log(Level.WARNING, file.getAbsolutePath(), ex);
            }
            
            String attachment_header = extractFieldFromHeaderMap(headerMap,"X-MS-Has-Attach");
            if (attachment_header != null && attachment_header.equals("yes")){
                email.setHasAttachment(Boolean.TRUE);
            }   
            
            String received_header = extractFieldFromHeaderMap(headerMap,"Received");
            email.setReceive_date(extractDateFromRFCDate(received_header));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MimeEmailParser.class.getName()).log(Level.SEVERE, file.getAbsolutePath(), ex);
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(MimeEmailParser.class.getName()).log(Level.SEVERE, file.getAbsolutePath(), ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(MimeEmailParser.class.getName()).log(Level.SEVERE, file.getAbsolutePath(), ex);
            }
        }
        
    }
    
    protected Date extractDateFromRFCDate(String received_header){
//        String rfcDate = "Sat, 13 Mar 2010 11:29:05 -0800";
        String rfcDate = null;               
        Date javaDate = null;
        //String regPattern = "(?:(Sun|Mon|Tue|Wed|Thu|Fri|Sat),\\s+)?(\\d{1,2})\\s+(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s+(\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2})\\s+(-|\\+)\\d{4}";
        String regPattern = "(\\d{1,2})\\s+(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s+(\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2})\\s+(-|\\+)\\d{4}";
        
        if (received_header != null){
            Pattern p = Pattern.compile(regPattern);
            Matcher m = p.matcher(received_header);
            if (m.find()){
                rfcDate = m.group();
            }

            //String datePattern = "EEE, dd MMM yyyy HH:mm:ss Z";
            String datePattern = "dd MMM yyyy HH:mm:ss Z";
            SimpleDateFormat format = new SimpleDateFormat(datePattern);
            if (rfcDate != null){
                try {
                    javaDate = format.parse(rfcDate.replace("\r\n", ""));
                } catch (ParseException ex) {
                    Logger.getLogger(MimeEmailParser.class.getName()).log(Level.SEVERE, email.getFilePath(), ex);
                }
            }
        }
        return javaDate;
    }
    
    protected Map<String, String> convertHeadersToMap(Enumeration<?> headers){
        Map<String, String> headerMap = new HashMap<>();
        while (headers.hasMoreElements()){
            Header header = (Header)headers.nextElement();
            headerMap.put(header.getName(), header.getValue());
        }        
        return headerMap;
    }
    
    protected String extractFieldFromHeaderMap(Map<String, String> headerMap, String headerName){
        return headerMap.get(headerName);
    }
   
    protected String extractNameFromAddress(String address){
        if (address != null){
            if (address.indexOf("<") > 0){
                return address.substring(0, address.indexOf("<")-1);
            }else{
                return address;
            }
        }else{
            return "";
        }
    }
    
    protected String extractEmailFromAddress(String address){
        if (address != null){
            if (address.indexOf("<") > 0){
                return address.substring(address.indexOf("<")+1,address.indexOf(">"));
            }else{
                return address;
            }
        }else{
            return "";
        }
    }

    protected List<String> convertAddressArrayToList(Address[] array){
        List<String> list = new ArrayList<String>();
        if (array != null){
            for (int i = 0; i < array.length; i++){
                list.add(extractNameFromAddress(array[i].toString()));
            }
        }
        return list;
    }
    
    public Email getEmail(){
        return email;
    }
}
