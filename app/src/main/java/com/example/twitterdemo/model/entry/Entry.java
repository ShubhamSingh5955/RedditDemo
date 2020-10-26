package com.example.twitterdemo.model.entry;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name="entry", strict=false)
public class Entry implements Serializable {

    @Element(name ="content")
    private String cotent;

    @Element(required =false, name ="author")
    private Author author;

    @Element(name ="id")
    private String id;


    @Element(name ="updated",required = true)
    private String updated;

    @Element(name ="title")
    private String title;

    public Entry() {
    }

    public Entry(String cotent, Author author, String title, String updated) {
        this.cotent = cotent;
        this.author = author;
        this.title = title;
        this.updated = updated;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "\n"+"Entry{" +
                "cotent='" + cotent + '\'' +
                ", author='" + author + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", updated='" + updated + '\'' +
                '}'+"\n"+
                "------------------------------------------------------";
    }
}
