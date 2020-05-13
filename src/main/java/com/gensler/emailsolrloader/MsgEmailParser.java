/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gensler.emailsolrloader;

import com.auxilii.msgparser.MsgParser;
import com.auxilii.msgparser.RecipientEntry;
import com.auxilii.msgparser.attachment.Attachment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;

/**
 *
 * @author Eric Theis <eric_theis@gensler.com>
 */
public final class MsgEmailParser {
    private Email email;

    public MsgEmailParser(File file) {
//        System.out.println(file.getAbsolutePath());
        try {
            email = new Email();
            
            MsgParser msgp = new MsgParser();
            com.auxilii.msgparser.Message msg = msgp.parseMsg(file);
            
            email.setFilePath(file.getAbsolutePath());
            
            email.setId(msg.getMessageId());
            email.setSender(msg.getFromName());
            email.setSubject(msg.getSubject());
            email.setMessage(msg.getBodyText());
            
            List<RecipientEntry> recipientsTo = msg.getRecipients();
            email.setRecipient_to(extractStringListFromRecipientList(recipientsTo));
            
            List<RecipientEntry> recipientsCc = msg.getCcRecipients();
            email.setRecipient_cc(extractStringListFromRecipientList(recipientsCc));
            
            
            List<Attachment> attachments = msg.getAttachments();
            if (!attachments.isEmpty()){
                email.setHasAttachment(Boolean.TRUE);
            }
            
            email.setReceive_date(msg.getDate());
        } catch (IOException ex) {
            Logger.getLogger(MsgEmailParser.class.getName()).log(Level.SEVERE, file.getAbsolutePath(), ex);
        } catch (UnsupportedOperationException ex) {
            Logger.getLogger(MsgEmailParser.class.getName()).log(Level.SEVERE, file.getAbsolutePath(), ex);
        } catch (Throwable ex) {
            Logger.getLogger(MsgEmailParser.class.getName()).log(Level.SEVERE, file.getAbsolutePath(), ex);
        }
    }
    
    
    public Email getEmail(){
        return email;
    }
    
    protected List<String> extractStringListFromRecipientList(List<RecipientEntry> recipients){
        List<String> list = new ArrayList<>();
        for(RecipientEntry recipient : recipients){
            list.add(recipient.getToName());
        }
        return list;
    }
    
}
