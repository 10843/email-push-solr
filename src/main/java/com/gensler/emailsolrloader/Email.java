/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gensler.emailsolrloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Eric Theis <eric_theis@gensler.com>
 */
public class Email {

    private String id;
    private List<String> recipient_to;
    private List<String> recipient_cc;
    private String sender;
    private String subject;
    private String message;
    private Date receive_date;
    private Boolean hasAttachment;
    private Boolean isUgrent;
    private String filePath;
    
    public Email() {
        id = "";
        recipient_to = new ArrayList<String>();
        recipient_cc = new ArrayList<String>();
        sender = "";
        subject = "";
        message = "";
        this.receive_date = new Date();
        this.hasAttachment = false;
        this.isUgrent = false;
        this.filePath = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public List<String> getRecipient_to() {
        return recipient_to;
    }

    public void setRecipient_to(List<String> recipient_to) {
        this.recipient_to = recipient_to;
    }
    
    public void addRecipient_to(String recipient_to){
        this.recipient_to.add(recipient_to);
    }

    public List<String> getRecipient_cc() {
        return recipient_cc;
    }

    public void setRecipient_cc(List<String> recipient_cc) {
        this.recipient_cc = recipient_cc;
    }

    public void addRecipient_cc(String recipient_cc){
        this.recipient_cc.add(recipient_cc);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(Date receive_date) {
        this.receive_date = receive_date;
    }

    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(Boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public Boolean getIsUgrent() {
        return isUgrent;
    }

    public void setIsUgrent(Boolean isUgrent) {
        this.isUgrent = isUgrent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.recipient_to);
        hash = 11 * hash + Objects.hashCode(this.sender);
        hash = 11 * hash + Objects.hashCode(this.subject);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Email other = (Email) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.recipient_to, other.recipient_to)) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        return true;
    }


    
}
