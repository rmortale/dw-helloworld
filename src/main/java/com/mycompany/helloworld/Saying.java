/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloworld;


/**
 *
 * @author vidoa
 */
public class Saying {

    private final long id;
    private final String content;

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
