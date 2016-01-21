/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gensler.emailsolrloader;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

/**
 *
 * @author Eric Theis <eric_theis@gensler.com>
 */
public class SolrEmailInsert {
    private SolrServer solr = null;
    
    public SolrEmailInsert() {

        String urlString = "http://localhost:8983/solr/collection1";
        solr = new HttpSolrServer(urlString);
    }
    
    public boolean addEmailToIndex(Email email){
        boolean success = false;
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", email.getId());
        for(String to : email.getRecipient_to()){
            doc.addField("recipient_to",to);
        }
        for (String cc : email.getRecipient_cc()){
           doc.addField("recipient_cc",cc);
        }
        doc.addField("sender",email.getSender());
        doc.addField("subject",email.getSubject());
        doc.addField("message",email.getMessage());
        doc.addField("receive_date",email.getReceive_date());
        doc.addField("has_attachements", email.getHasAttachment());
        
        try {
            UpdateResponse response = solr.add(doc);
            solr.commit();
            success = true;
        } catch (SolrServerException ex) {
            Logger.getLogger(SolrEmailInsert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SolrEmailInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return success;        
    }

}
