/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.forum;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author rubenpaixao
 */
class Topic {
    int id;
    String title;
    Date date ;
    LinkedList<TopicAnswer> answer = new LinkedList<TopicAnswer>();
    
}