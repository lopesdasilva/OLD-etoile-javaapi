/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.forum;

import java.util.LinkedList;

/**
 *
 * @author rubenpaixao
 */
public class Forum {
    
    int id;
    String title;
    LinkedList<Topic> topics = new LinkedList<Topic>();
    
    public Forum(int id, String title){
        this.id=id;
        this.title=title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(LinkedList<Topic> topics) {
        this.topics = topics;
    }

    public void addTopic(Topic t) {
        System.out.println("Adicionar tópico");
        topics.add(t);
    }

    public void clearTopics() {
        topics = new LinkedList<Topic>();
    }
    
    
    
    
    
}
