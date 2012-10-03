/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.question;

import java.io.Serializable;

/**
 *
 * @author Rui
 */
public class URL implements Serializable{
    int id;
    
    String url;
    
    String name;
    
    String username;
    
    int votes;
    
    int n_votes;
    
    int average;
    
    int aux_vote;

    public int getAux_vote() {
        return aux_vote;
    }
    
    

    public URL(int id, String name, String url, String username, int votes, int n_votes, int average) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.username = username;
        this.votes = votes;
        this.n_votes = n_votes;
        this.average = average;
        
    }

    public int getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    
    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getVotes(){
        return votes;
    }
    
    public void setVotes(int stars){
        this.votes=this.votes+stars;
        this.n_votes++;
    }
    
    public void setAverage(int stars){
     aux_vote=stars;
     average = stars;
    }
    
    public int getNVotes(){
        return n_votes;
    }
    
    public int getAverage(){
        return average;
    }
}
    
