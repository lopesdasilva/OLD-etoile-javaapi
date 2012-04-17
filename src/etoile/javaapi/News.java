/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

/**
 *
 * @author Ruben
 */


public class News {
    int id;
    String title;
    String url;
    String text;

    public News(int id, String title, String url, String text) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.text = text;
    }
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
 
}
