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
import javax.mail.Message;

/**
 *
 * @author Eric Theis <eric_theis@gensler.com>
 */
public class MsgEmailParser {
    private Email email;

    public MsgEmailParser(File file) throws IOException{
        email = new Email();
        
        MsgParser msgp = new MsgParser();
        com.auxilii.msgparser.Message msg = msgp.parseMsg(file);

        email.setFilePath(file.getAbsolutePath());
        
        email.setId(msg.getMessageId());
        email.setSender(msg.getFromName());
        email.setSubject(msg.getSubject());
        email.setMessage(msg.getBodyText());
        
        List<RecipientEntry> recipient_to = msg.getRecipients();
        List<String> to = new ArrayList<>();
        for(RecipientEntry recipient : recipient_to){
            to.add(recipient.getToName());
        }
        email.setRecipient_to(to);

        
        List<Attachment> attachments = msg.getAttachments();
        if (!attachments.isEmpty()){
            email.setHasAttachment(Boolean.TRUE);
        }
        
        email.setReceive_date(msg.getDate());
        
    }
    
    
    public Email getEmail(){
        return email;
    }
    
}
