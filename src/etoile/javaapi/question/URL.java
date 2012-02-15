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
    
    String url;
    
    String name;
    
    String username;

    public URL(String url, String name, String username) {
        this.url = url;
        this.name = name;
        this.username = username;
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
    
    
}
